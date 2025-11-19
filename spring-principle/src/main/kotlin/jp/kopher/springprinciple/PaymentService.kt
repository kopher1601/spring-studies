package jp.kopher.springprinciple

import java.math.BigDecimal
import java.time.LocalDateTime

class PaymentService(
    private val exRateProvider: ExRateProvider = WebApiExRateProvider()
) {

    fun prepare(
        orderId: Long,
        currency: String,
        foreignCurrencyAmount: BigDecimal,
    ): Payment {
        val exRate = exRateProvider.getExRate(currency)

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
}