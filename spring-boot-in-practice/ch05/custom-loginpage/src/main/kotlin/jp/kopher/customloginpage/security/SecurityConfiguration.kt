package jp.kopher.customloginpage.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler

@Configuration
class SecurityConfiguration(
    private val customAccessDeniedHandler: AccessDeniedHandler
) {

    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        val user = User.withUsername("user").password("password").roles("USER").build()
        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authz ->
            authz.requestMatchers("/login").permitAll()
                .requestMatchers("/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        }
        http.exceptionHandling { exception ->
            exception.accessDeniedHandler(customAccessDeniedHandler)
        }
        http.formLogin { form ->
            form.loginPage("/login")
        }
        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring().requestMatchers("/webjars/**", "/images/**", "/css/**", "/h2-console/**")
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}
