package jp.kopher.springsecurityinaction.filters

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class AuthenticationLoggingFilter: Filter {

    private val log = org.slf4j.LoggerFactory.getLogger(AuthenticationLoggingFilter::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest

        httpServletRequest.getHeader("Request-Id")?.let { requestId ->
            log.info("Request ID: {}", requestId)
            chain?.doFilter(request, response)
        }
    }
}