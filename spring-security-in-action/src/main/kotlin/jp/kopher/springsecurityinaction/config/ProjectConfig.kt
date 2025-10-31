package jp.kopher.springsecurityinaction.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class ProjectConfig(
    private val successHandler: AuthenticationSuccessHandler,
    private val failureHandler: AuthenticationFailureHandler,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.formLogin{
            it.successHandler(successHandler)
            it.failureHandler(failureHandler)
        }
        http.authorizeHttpRequests{
            it.anyRequest().authenticated()
        }
        return http.build()
    }
}