package jp.kopher1601.springsecurity.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.formLogin{ form -> form.disable()}
        http.httpBasic{ basic -> basic.disable()}
        http.authorizeHttpRequests { requests -> requests
            .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
            .requestMatchers("/notices", "/contact", "/error").permitAll()
        }

        return http.build()
    }
}