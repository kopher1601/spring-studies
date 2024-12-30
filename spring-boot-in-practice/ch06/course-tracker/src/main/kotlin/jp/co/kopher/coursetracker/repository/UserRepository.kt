package jp.co.kopher.coursetracker.repository

import jp.co.kopher.coursetracker.model.ApplicationUser
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<ApplicationUser, Long> {

    fun findByUsername(username: String): ApplicationUser?
}