package jp.co.kopher1601.kotlinpractice.domain.entity

import jakarta.persistence.*

@Entity
class Link(

    private val name: String,
    private val content: String,
    private val isActive: Boolean,

    @Id @Column(name = "link_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
): BaseEntity()