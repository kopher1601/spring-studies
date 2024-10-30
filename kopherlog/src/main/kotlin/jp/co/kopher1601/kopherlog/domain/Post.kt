package jp.co.kopher1601.kopherlog.domain

import jakarta.persistence.*

@Entity
class Post(

    private val title: String,

    @Lob
    private val content: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
)