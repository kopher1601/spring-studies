package jp.co.kopher1601.kotlinpractice.domain.entity

import jakarta.persistence.*

@Entity
class HttpInterface(


    @Id @Column(name = "http_interface_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
): BaseEntity()