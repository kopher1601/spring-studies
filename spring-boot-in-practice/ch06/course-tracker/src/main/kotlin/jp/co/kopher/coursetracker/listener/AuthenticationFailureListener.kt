package jp.co.kopher.coursetracker.listener

import jp.co.kopher.coursetracker.service.LoginAttemptService
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent

class AuthenticationFailureListener(
    private val loginAttemptService: LoginAttemptService,
): ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    override fun onApplicationEvent(event: AuthenticationFailureBadCredentialsEvent) {
        val username = event.authentication.principal as String
        loginAttemptService.loginFailed(username)
    }
}