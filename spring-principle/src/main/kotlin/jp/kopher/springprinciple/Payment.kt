package jp.kopher.springprinciple

import java.math.BigDecimal
import java.time.LocalDateTime

class Payment(
    val orderId: Long,
    val currency: String,
    val foreignCurrencyAmount: BigDecimal,
    val exRate: BigDecimal,
    val convertedAmount: BigDecimal,
    val validUntil: LocalDateTime,
) {
    override fun toString(): String {
        return "Payment(orderId=$orderId, currency='$currency', foreignCurrencyAmount=$foreignCurrencyAmount, exRate=$exRate, convertedAmount=$convertedAmount, validUntil=$validUntil)"
    }
}
