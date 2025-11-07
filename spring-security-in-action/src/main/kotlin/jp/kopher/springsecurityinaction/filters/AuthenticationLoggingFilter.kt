package jp.kopher.springsecurityinaction.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class AuthenticationLoggingFilter: OncePerRequestFilter() {

    private val log = org.slf4j.LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.getHeader("Request-Id").let {
            log.info("Request-Id: $it")
        }
        filterChain.doFilter(request, response)
    }
}