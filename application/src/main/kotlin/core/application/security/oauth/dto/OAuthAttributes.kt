package core.application.security.oauth.dto

import core.application.member.memberOAuth.domain.model.OAuthProvider
import core.application.security.oauth.exception.UnsupportedOAuthProviderException

interface OAuthAttributes {
    fun getExternalId(): String

    fun getProvider(): OAuthProvider

    fun getEmail(): String

    fun getName(): String

    companion object {
        fun of(
            providerId: String,
            attributes: Map<String, Any>,
        ): OAuthAttributes =
            when {
                OAuthProvider.KAKAO.isProviderOf(providerId) -> KakaoAuthAttributes.of(attributes)
                else -> throw UnsupportedOAuthProviderException()
            }
    }
}
