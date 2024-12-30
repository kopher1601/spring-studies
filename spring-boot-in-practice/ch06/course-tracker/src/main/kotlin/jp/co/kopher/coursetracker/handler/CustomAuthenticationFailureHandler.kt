package jp.co.kopher.coursetracker.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Service

@Service
class CustomAuthenticationFailureHandler(
    private val defaultRedirectStrategy: DefaultRedirectStrategy = DefaultRedirectStrategy()
) : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        if (exception is DisabledException) {
            defaultRedirectStrategy.sendRedirect(request, response, "/login-disabled")
            return
        }

        if (exception?.cause is LockedException) {
            defaultRedirectStrategy.sendRedirect(request, response, "/login-locked")
            return
        }
        defaultRedirectStrategy.sendRedirect(request, response, "/login-error")
    }
}