package jp.kopher.springprinciple.payment

import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Component
class PaymentService(
    private val exRateProvider: ExRateProvider,
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