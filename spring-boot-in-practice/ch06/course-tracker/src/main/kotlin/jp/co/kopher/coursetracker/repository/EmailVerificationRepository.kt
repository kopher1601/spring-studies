package jp.co.kopher.coursetracker.repository

import jp.co.kopher.coursetracker.model.EmailVerification
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmailVerificationRepository : CrudRepository<EmailVerification, String> {
    fun findByUsername(username: String): EmailVerification?
    fun existsByUsername(username: String): Boolean
}