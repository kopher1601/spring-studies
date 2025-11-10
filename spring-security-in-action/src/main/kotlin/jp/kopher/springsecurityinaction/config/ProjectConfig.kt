package jp.kopher.springsecurityinaction.config

import jp.kopher.springsecurityinaction.security.DocumentPermissionEvaluator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
class ProjectConfig(
    private val evaluator: DocumentPermissionEvaluator,
) {

    @Bean
    fun methodSecurityExpressionHandler(): MethodSecurityExpressionHandler {
        val handler = DefaultMethodSecurityExpressionHandler()
        handler.setPermissionEvaluator(evaluator)
        return handler
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("natalie")
            .password("12345")
            .roles("admin")
            .build()
        val admin = User.withUsername("emma")
            .password("12345")
            .roles("manager")
            .build()
        return InMemoryUserDetailsManager(user, admin)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}