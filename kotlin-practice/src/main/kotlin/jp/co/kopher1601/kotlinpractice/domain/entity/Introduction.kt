package jp.co.kopher1601.kotlinpractice.domain.entity

import jakarta.persistence.*

@Entity
class Introduction(
    private val content: String,
    private val isActive: Boolean,

    @Id @Column(name = "introduction_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
): BaseEntity()