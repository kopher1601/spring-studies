package jp.kopher.springsecurityinaction.filters

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class StaticKeyAuthenticationFilter(
    @Value("\${authorization.key}")
    private var authorizationKey: String,
) : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse

        httpServletRequest.getHeader("Authorization")?.let {
            if (authorizationKey == it) {
                chain?.doFilter(request, response)
                return
            }
        }
        httpServletResponse.status = HttpServletResponse.SC_UNAUTHORIZED
    }
}