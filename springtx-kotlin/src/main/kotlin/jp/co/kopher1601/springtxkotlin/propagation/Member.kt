package jp.co.kopher1601.springtxkotlin.propagation

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(

    private val username: String,

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null,
)