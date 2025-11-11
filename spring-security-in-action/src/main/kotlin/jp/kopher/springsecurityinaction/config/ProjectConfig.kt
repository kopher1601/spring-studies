package jp.kopher.springsecurityinaction.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager().also {
            it.createUser(User.withUsername("john").password("12345").authorities("read").build())
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http.authorizeHttpRequests {
        it.anyRequest().authenticated()
    }.build()
}