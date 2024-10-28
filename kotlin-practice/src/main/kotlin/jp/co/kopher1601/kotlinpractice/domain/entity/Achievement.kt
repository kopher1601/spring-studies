package jp.co.kopher1601.kotlinpractice.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Achievement(
    private val title: String,
    private val description: String,
    private val achieveDate: LocalDate?,
    private val host: String,
    private val isActive: Boolean,

    @Id
    @Column(name = "achievement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
): BaseEntity()