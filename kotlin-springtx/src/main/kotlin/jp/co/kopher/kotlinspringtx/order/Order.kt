package jp.co.kopher.kotlinspringtx.order

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order(

    var username: String? = "정상", // 정상, 예외, 잔고부족
    var payStatus: String? = "대기", // 대기, 완료

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)