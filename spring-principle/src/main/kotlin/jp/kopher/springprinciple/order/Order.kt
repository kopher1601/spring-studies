package jp.kopher.springprinciple.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue
    val id: Long,

    @Column(unique = true)
    val no: String,

    val total: BigDecimal,
)