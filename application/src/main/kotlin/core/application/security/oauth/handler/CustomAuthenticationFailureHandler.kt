package core.application.security.oauth.handler

import core.application.security.oauth.exception.OAuthExceptionCode
import core.application.security.properties.SecurityProperties
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationFailureHandler(
    private val securityProperties: SecurityProperties,
) : AuthenticationFailureHandler {
    /**
     * 소셜 로그인 인증 실패 시 호출되는 메서드로, 사용자를 오류 페이지로 리다이렉트함.
     *
     * @author LeeHanEum
     * @since 2025.07.12
     */
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?,
    ) {
        response?.sendRedirect(
            securityProperties.restrictedRedirectUrl + "?error=true&exception=" +
                OAuthExceptionCode.AUTHENTICATION_FAILED,
        )
    }
}
