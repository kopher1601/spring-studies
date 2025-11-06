package jp.kopher.springsecurityinaction.filters

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class RequestValidationFilter: Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse

        val requestId = httpServletRequest.getHeader("Request-Id")

        if (requestId.isNullOrBlank()) {
            httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
            return
        }

        filterChain?.doFilter(request, response)
    }
}