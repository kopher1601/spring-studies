package jp.kopher1601.springsecurity.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { requests -> requests
            .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
            .requestMatchers("/notices", "/contact", "/error").permitAll()
        }
        http.formLogin(withDefaults())
        http.httpBasic(withDefaults())
        return http.build()
    }
}