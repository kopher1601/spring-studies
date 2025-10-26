package jp.kopher.springsecurityinaction.security

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider: AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val username = authentication?.name
        val password = authentication?.credentials.toString()

        // 여기의 if-else 절의 조건은 UserDetailsService 및 PasswordEncoder 의 책임을 대체하고 있다.
        if (username == "john" && password == "12345") {
            return UsernamePasswordAuthenticationToken(username, password, arrayListOf())
        } else {
            throw AuthenticationCredentialsNotFoundException("Error in authentication!")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}