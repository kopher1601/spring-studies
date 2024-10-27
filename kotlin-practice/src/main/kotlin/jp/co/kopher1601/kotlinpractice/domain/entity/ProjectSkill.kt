package jp.co.kopher1601.kotlinpractice.domain.entity

import jakarta.persistence.*

@Entity
class ProjectSkill(


    @Id @Column(name = "project_skill_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
): BaseEntity()