package jp.co.kopher1601.kotlinpractice.domain.entity

import jakarta.persistence.*
import jp.co.kopher1601.kotlinpractice.domain.constant.SkillType

@Entity
class Skill(
    private val name: String,

    @Column(name = "skill_type")
    @Enumerated(EnumType.STRING)
    private val type: SkillType,

    private val isActive: Boolean,

    @Id @Column(name = "skill_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
): BaseEntity()