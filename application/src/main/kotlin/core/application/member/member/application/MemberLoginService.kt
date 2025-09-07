package core.application.member.member.application

import core.application.member.member.application.exception.MemberIdRequiredException
import core.application.member.member.domain.model.Member
import core.application.member.member.domain.model.MemberId
import core.application.member.member.domain.port.inbound.HandleMemberLoginUseCase
import core.application.member.member.domain.port.outbound.MemberPersistencePort
import core.application.member.memberAuthority.application.MemberAuthorityService
import core.application.member.memberOAuth.application.MemberOAuthService
import core.application.refreshToken.domain.model.RefreshToken
import core.application.refreshToken.domain.port.outbound.RefreshTokenPersistencePort
import core.application.security.oauth.dto.LoginResult
import core.application.security.oauth.dto.OAuthAttributes
import core.application.security.oauth.token.JwtTokenProvider
import core.application.security.properties.SecurityProperties
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberLoginService(
    private val memberPersistencePort: MemberPersistencePort,
    private val memberAuthorityService: MemberAuthorityService,
    private val memberOAuthService: MemberOAuthService,
    private val refreshTokenPersistencePort: RefreshTokenPersistencePort,
    private val securityProperties: SecurityProperties,
    private val tokenProvider: JwtTokenProvider,
    private val environment: Environment,
) : HandleMemberLoginUseCase {
    @Transactional
    override fun handleLoginSuccess(
        requestDomain: String,
        authAttributes: OAuthAttributes,
    ): LoginResult =
        memberPersistencePort
            .findByEmail(authAttributes.getEmail())
            ?.let { member -> handleExistingMemberLogin(requestDomain, member) }
            ?: handleUnregisteredMember(authAttributes)

    private fun generateLoginResult(
        memberId: MemberId,
        redirectUrl: String,
    ): LoginResult {
        val newToken = tokenProvider.generateRefreshToken(memberId.toString())
        val refreshToken =
            refreshTokenPersistencePort
                .findByMemberId(memberId)
                ?.apply { rotate(newToken) }
                ?: RefreshToken.create(memberId, newToken)
        val savedToken = refreshTokenPersistencePort.save(refreshToken)

        return LoginResult(savedToken, redirectUrl)
    }

    private fun handleExistingMemberLogin(
        requestDomain: String,
        member: Member,
    ): LoginResult {
        member.id?.value ?: return LoginResult(null, securityProperties.restrictedRedirectUrl)

        if (!member.isAllowed() || memberPersistencePort.existsDeletedMemberById(member.id.value)) {
            return LoginResult(null, securityProperties.restrictedRedirectUrl)
        }

        return generateLoginResult(member.id, buildRedirectUrlByRoleAndDomain(requestDomain, member.id))
    }

    private fun buildRedirectUrlByRoleAndDomain(
        requestDomain: String,
        memberId: MemberId,
    ) = when {
        hasAdminRole(memberId) -> adminRedirectUrl(requestDomain)
        else -> securityProperties.coreRedirectUrl + "?$IS_ADMIN_FALSE"
    }

    private fun adminRedirectUrl(requestDomain: String): String {
        if (environment.activeProfiles.contains(PROFILE_LOCAL)) {
            return "${securityProperties.adminRedirectUrl}?$IS_ADMIN_TRUE"
        }

        val coreSuffix = if (environment.activeProfiles.contains(PROFILE_DEV)) CLIENT_SUFFIX else CORE_SUFFIX

        val redirectUrl =
            when (requestDomain) {
                "$coreSuffix.${securityProperties.cookie.domain}" -> {
                    "${securityProperties.coreRedirectUrl}?$IS_ADMIN_TRUE"
                }

                "$ADMIN_SUFFIX.${securityProperties.cookie.domain}" -> {
                    "${securityProperties.adminRedirectUrl}?$IS_ADMIN_TRUE"
                }

                else -> {
                    "${securityProperties.adminRedirectUrl}?$IS_ADMIN_TRUE"
                }
            }

        return redirectUrl
    }

    private fun hasAdminRole(memberId: MemberId): Boolean =
        memberAuthorityService
            .getAuthorityNamesByMemberId(memberId)
            .any { it in ADMIN_AUTHORITIES }

    private fun handleUnregisteredMember(authAttributes: OAuthAttributes): LoginResult {
        val member =
            memberPersistencePort.save(Member.create(authAttributes.getEmail(), authAttributes.getName(), environment))
        memberOAuthService.addMemberOAuthProvider(member, authAttributes)

        return generateLoginResult(
            member.id ?: throw MemberIdRequiredException(),
            securityProperties.restrictedRedirectUrl,
        )
    }

    companion object {
        private val ADMIN_AUTHORITIES = setOf("ORGANIZER")
        private const val IS_ADMIN_TRUE = "isAdmin=true"
        private const val IS_ADMIN_FALSE = "isAdmin=false"
        private const val ADMIN_SUFFIX = "admin"
        private const val CORE_SUFFIX = "core"
        private const val CLIENT_SUFFIX = "client"
        private const val PROFILE_DEV = "dev"
        private const val PROFILE_LOCAL = "local"
    }
}
