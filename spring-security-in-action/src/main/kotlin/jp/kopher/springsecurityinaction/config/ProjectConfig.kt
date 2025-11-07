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
class ProjectConfig(
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.formLogin { form ->
            form.defaultSuccessUrl("/main", true)
        }.authorizeHttpRequests {
            it.anyRequest().authenticated()
        }
        http.csrf { it.ignoringRequestMatchers("/ciao") }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        InMemoryUserDetailsManager().let { manager ->
            val u1 = User.withUsername("mary")
                .password("12345")
                .authorities("READ")
                .build()

            manager.createUser(u1)
            return manager
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}