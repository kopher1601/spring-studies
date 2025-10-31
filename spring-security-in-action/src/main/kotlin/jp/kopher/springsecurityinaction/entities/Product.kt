package jp.kopher.springsecurityinaction.entities

import jakarta.persistence.*
import jp.kopher.springsecurityinaction.entities.enums.Currency

@Entity
class Product(

    val name: String,
    val price: Double,

    @Enumerated(value = EnumType.STRING)
    val currency: Currency,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)