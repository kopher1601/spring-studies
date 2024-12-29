package jp.kopher.customloginpage.repository

import jp.kopher.customloginpage.model.ApplicationUser
import org.springframework.data.repository.CrudRepository

interface ApplicationUserRepository : CrudRepository<ApplicationUser, Long> {

    fun findByUsername(username: String): ApplicationUser?
}