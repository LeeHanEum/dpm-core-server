package core.application.authority.application

import core.application.authority.application.exception.AuthorityNotFoundException
import core.application.authority.domain.model.AuthorityId
import core.application.authority.domain.model.AuthorityType
import core.application.authority.domain.port.inbound.AuthorityQueryUseCase
import core.application.authority.domain.port.outbound.AuthorityPersistencePort
import core.application.authority.presentation.response.AuthorityListResponse
import core.application.member.member.domain.model.MemberId
import org.springframework.stereotype.Service

@Service
class AuthorityQueryService(
    private val authorityPersistencePort: AuthorityPersistencePort,
) : AuthorityQueryUseCase {
    fun getAllAuthorities(): AuthorityListResponse = AuthorityListResponse.from(authorityPersistencePort.findAll())

    override fun getAuthoritiesByExternalId(externalId: String): List<String> =
        authorityPersistencePort
            .findAllByMemberExternalId(externalId)
            .ifEmpty { listOf("GUEST") }

    override fun getAuthoritiesByMemberId(memberId: MemberId): List<String> =
        authorityPersistencePort
            .findAllByMemberId(memberId)
            .ifEmpty { listOf("GUEST") }

    override fun getAuthorityIdByType(authorityType: AuthorityType): AuthorityId =
        authorityPersistencePort.findAuthorityIdByName(authorityType.toString())
            ?: throw AuthorityNotFoundException()
}
