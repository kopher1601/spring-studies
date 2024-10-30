package jp.co.kopher1601.springtxkotlin.order

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order(
    private val _username: String,
    private var _payStatus: String = "대기",

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    val username: String
        get() = _username

    fun updateStatus(status: String) {
        this._payStatus = status
    }

    val payStatus: String
        get() = _payStatus

}