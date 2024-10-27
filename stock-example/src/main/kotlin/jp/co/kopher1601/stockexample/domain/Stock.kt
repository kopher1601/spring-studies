package jp.co.kopher1601.stockexample.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Stock(
    private val productId: Long,
    private var _quantity: Long,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
){

    val quantity: Long
        get() = this._quantity

    fun decrease(quantity: Long) {
        if (this._quantity - quantity < 0) {
            throw IllegalStateException("재고는 0개 미만이 될 수 없습니다.")
        }

        this._quantity -= quantity
    }
}