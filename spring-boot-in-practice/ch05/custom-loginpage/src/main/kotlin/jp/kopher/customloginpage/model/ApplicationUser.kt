package jp.kopher.customloginpage.model

import jakarta.persistence.*

@Entity
@Table(name = "CT_USERS")
class ApplicationUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val verified: Boolean = false,
    val locked: Boolean,
    @Column(name = "ACC_CRED_EXPIRED")
    val accountCredentialsExpired: Boolean,
) {
}