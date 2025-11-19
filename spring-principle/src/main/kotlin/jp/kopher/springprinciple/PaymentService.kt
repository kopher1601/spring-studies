package jp.kopher.springprinciple

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.URL
import java.time.LocalDateTime
import java.util.stream.Collectors

class PaymentService {

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

    private fun getExRate(currency: String): BigDecimal {
        val url = URL(" https://open.er-api.com/v6/latest/${currency}")
        val connection = url.openConnection()
        val br = BufferedReader(InputStreamReader(connection.getInputStream(), "UTF-8"))
        val response = br.lines().collect(Collectors.joining())
        br.close()

        val mapper = jacksonObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        val exRate = data.rates["KRW"] ?: BigDecimal.ZERO
        return exRate
    }
}

fun main() {
    val paymentService = PaymentService()
    val payment = paymentService.prepare(100, "USD", BigDecimal.valueOf(50.7))
    println("paymentService = $payment")
}