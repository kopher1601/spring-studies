package jp.kopher.springsecurityinaction

import org.springframework.security.test.context.support.WithSecurityContext

@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = CustomSecurityContextFactory::class)
annotation class WithCustomUser(
    val username: String
)
