package jp.kopher.springsecurityinaction.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
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
    fun securityFilterChain(http:HttpSecurity): SecurityFilterChain {
        http.httpBasic(Customizer.withDefaults())
        http.authorizeHttpRequests{
            it.anyRequest().hasAuthority("WRITE")
        }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val manager = InMemoryUserDetailsManager()

        val user1 = User.withUsername("john")
            .password("12345")
            .authorities("READ")
            .build()
        val user2 = User.withUsername("jane")
            .password("12345")
            .authorities("WRITE")
            .build()

        manager.createUser(user1)
        manager.createUser(user2)

        return manager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}