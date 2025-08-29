package jp.kopher1601.springsecurity.events

import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.stereotype.Component

@Component
class AuthenticationEvents {

    val log = LoggerFactory.getLogger(AuthenticationEvents::class.java)

    @EventListener
    fun onSuccess(successEvent: AuthenticationSuccessEvent) {
        log.info("Login successful for the user : ${successEvent.authentication.name}")
    }

    @EventListener
    fun onFailure(failureEvent: AbstractAuthenticationFailureEvent) {
        log.info("Login failed for the user : ${failureEvent.authentication.name} due to : ${failureEvent.exception.message}")
    }

}