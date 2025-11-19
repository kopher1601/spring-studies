package jp.kopher.springprinciple

import java.math.BigDecimal
import java.time.LocalDateTime

abstract class PaymentService {

    fun prepare(
        orderId: Long,
        currency: String,
        foreignCurrencyAmount: BigDecimal,
    ): Payment {

        val exRate = getExRate(currency)

        val convertedAmount = foreignCurrencyAmount.multiply(exRate)

        val validUntil = LocalDateTime.now().plusMinutes(30)

        return Payment(
            orderId = orderId,
            currency = currency,
            foreignCurrencyAmount = foreignCurrencyAmount,
            exRate = exRate,
            convertedAmount = convertedAmount,
            validUntil = validUntil,
        )
    }

    abstract fun getExRate(currency: String): BigDecimal
}