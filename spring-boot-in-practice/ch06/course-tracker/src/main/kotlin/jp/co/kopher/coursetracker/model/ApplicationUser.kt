package jp.co.kopher.coursetracker.model

import jakarta.persistence.*

@Entity
@Table(name = "CT_USERS")
class ApplicationUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val firstName: String? = null,
    val lastName: String? = null,
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    @Column(name = "verified", nullable = false)
    var verified: Boolean? = null,
)