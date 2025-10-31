package jp.kopher.springsecurityinaction.entities

import jakarta.persistence.*

@Entity
class Authority(

    val name: String,

    @JoinColumn(name = "users")
    @ManyToOne
    val user: User,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)