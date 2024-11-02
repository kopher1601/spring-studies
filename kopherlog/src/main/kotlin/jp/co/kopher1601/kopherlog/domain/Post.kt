package jp.co.kopher1601.kopherlog.domain

import jakarta.persistence.*

@Entity
class Post(

    val title: String,

    @Lob
    val content: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)