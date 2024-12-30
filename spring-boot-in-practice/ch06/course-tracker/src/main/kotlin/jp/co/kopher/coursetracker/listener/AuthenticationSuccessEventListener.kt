package jp.co.kopher.coursetracker.listener

import jp.co.kopher.coursetracker.service.LoginAttemptService
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.core.userdetails.User

class AuthenticationSuccessEventListener(
    private val loginAttemptService: LoginAttemptService,
): ApplicationListener<AuthenticationSuccessEvent> {
    override fun onApplicationEvent(event: AuthenticationSuccessEvent) {
        val user = event.authentication.principal as User
        loginAttemptService.loginSuccess(user.username)
    }
}