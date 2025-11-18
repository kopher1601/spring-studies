package jp.kopher.springprinciple

import java.math.BigDecimal
import java.time.LocalDateTime

class PaymentService {
    fun prepare(
        orderId: Long,
        currency: String,
        foreignCurrencyAmount: BigDecimal,
    ): Payment {

        return Payment(
            orderId = orderId,
            currency = currency,
            foreignCurrencyAmount = foreignCurrencyAmount,
            exRate = BigDecimal.ZERO,
            convertedAmount = BigDecimal.ZERO,
            validUntil = LocalDateTime.now(),
        )
    }
}

fun main() {
    val paymentService = PaymentService()
    val payment = paymentService.prepare(100, "USD", BigDecimal.valueOf(50.7) )
    println("paymentService = $payment")
}