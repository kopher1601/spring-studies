package jp.kopher1601.springsecurity.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.formLogin(withDefaults())
        http.httpBasic(withDefaults())
        http.authorizeHttpRequests { requests -> requests
            .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
            .requestMatchers("/notices", "/contact", "/error").permitAll()
        }

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("user")
            // PasswordEncoder 를 설정하고 아무것도 패스워드에 prefix 로 지정하지 않으면 BCrypt 를 사용한다.
            // PasswordEncoderFactories 확인
            // NoOpPassworEncoder 사용
            .password("{noop}1234")
            .authorities("read")
            .build()

        val admin = User.withUsername("admin")
            // 기본값이 bcrypt 이기에 지정하지 않아도 돼지만 알기 쉽게 하기 위해 지정
            // BcryptPasswordEncoder 를 사용한다.
            .password("{bcrypt}\$2a\$12\$AeaCIF3AkFTU.PJGACW.xeiCZKn1sCOyWlcZfKB4P7lkTlN1VJ6aG")
            .authorities("admin")
            .build()

        return InMemoryUserDetailsManager(user,admin)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        // 스프링 시큐리티 팀이 지정한 팩토리 메서드(추상화)를 사용함으로써 구현체가 나중에 변경되어도 신경쓸필요가 없다
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}