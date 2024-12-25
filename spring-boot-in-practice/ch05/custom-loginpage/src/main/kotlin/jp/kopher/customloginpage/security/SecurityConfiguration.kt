package jp.kopher.customloginpage.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authz ->
            authz.requestMatchers("/login").permitAll().anyRequest().authenticated()
        }
        http.formLogin { form ->
            form.loginPage("/login")
        }
        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring().requestMatchers("/webjars/**","/images/**","/css/**","/h2-console/**")
        }
    }

}
