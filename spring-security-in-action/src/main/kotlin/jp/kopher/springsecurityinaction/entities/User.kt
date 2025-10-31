package jp.kopher.springsecurityinaction.entities

import jakarta.persistence.*
import jp.kopher.springsecurityinaction.entities.enums.EncryptionAlgorithm

@Table(name = "users")
@Entity
class User(

    val username: String,
    val password: String,

    @Enumerated(value = EnumType.STRING)
    val algorithm: EncryptionAlgorithm,

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    val authorities: List<Authority> = listOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)