package core.application.security.oauth.dto

import core.application.refreshToken.domain.model.RefreshToken

data class LoginResult(
    val refreshToken: RefreshToken?,
    val redirectUrl: String,
)
