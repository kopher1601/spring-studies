package jp.co.kopher.coursetracker.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build()

        val userDetailsManager = InMemoryUserDetailsManager()
        userDetailsManager.createUser(user)
        return userDetailsManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring().requestMatchers("/webjars/**", "/images/**", "/css/**", "/h2-console/**")
        }
    }

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        // https 활성화
        http.requiresChannel{chan ->
            chan.anyRequest().requiresSecure()
        }
        http.authorizeHttpRequests { authz ->
            authz.requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
        }
        http.formLogin { form ->
            form.loginPage("/login")
        }
        return http.build()
    }

}