package jp.kopher.springsecurityinaction.filters

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.csrf.CsrfToken

class CsrfTokenLogger: Filter {

    private val log = org.slf4j.LoggerFactory.getLogger(this::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest

        httpServletRequest.getAttribute("_csrf")?.let {
            val csrfToken = it as CsrfToken
            log.info("CSRF token: ${csrfToken.token}")
        }
        chain?.doFilter(request, response)
    }
}