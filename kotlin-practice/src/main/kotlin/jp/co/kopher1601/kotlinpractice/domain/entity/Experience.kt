package jp.co.kopher1601.kotlinpractice.domain.entity

import jakarta.persistence.*

@Entity
class Experience(


    @Id @Column(name = "experience_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
): BaseEntity()