package jp.kopher.springsecurityinaction

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory

class CustomSecurityContextFactory: WithSecurityContextFactory<WithCustomUser> {
    override fun createSecurityContext(annotation: WithCustomUser?): SecurityContext {
        val context = SecurityContextHolder.createEmptyContext()

        UsernamePasswordAuthenticationToken(annotation?.username, null, null).also {
            context.authentication = it
        }

        return context
    }
}