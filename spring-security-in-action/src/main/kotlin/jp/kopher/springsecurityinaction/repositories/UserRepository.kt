package jp.kopher.springsecurityinaction.repositories

import jp.kopher.springsecurityinaction.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findUserByUsername(username: String): User?
}