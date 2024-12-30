package jp.co.kopher.coursetracker.event

import jp.co.kopher.coursetracker.model.ApplicationUser
import org.springframework.context.ApplicationEvent


class UserRegistrationEvent(
    private val applicationUser: ApplicationUser,
): ApplicationEvent(applicationUser) {

    fun getUser(): ApplicationUser {
        return applicationUser
    }
}

