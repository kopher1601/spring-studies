package jp.co.kopher.coursetracker.model

import jakarta.persistence.*

@Entity
@Table(name = "CT_USERS")
class ApplicationUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val firstName: String? = null,
    val lastName: String? = null,
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    var verified: Boolean = false,
)