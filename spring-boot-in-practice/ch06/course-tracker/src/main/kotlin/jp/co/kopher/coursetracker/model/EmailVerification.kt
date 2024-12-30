package jp.co.kopher.coursetracker.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "CT_EMAIL_VERIFICATIONS")
class EmailVerification(
    @Id
    @GeneratedValue(generator = "UUID_GENERATOR")
    @UuidGenerator
    val verificationId: String = "",
    val username: String = "",
) {
}