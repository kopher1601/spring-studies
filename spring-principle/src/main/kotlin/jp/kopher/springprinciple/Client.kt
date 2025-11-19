package jp.kopher.springprinciple

import java.math.BigDecimal

class Client {
}

fun main() {
    val paymentService = PaymentService()
    val payment = paymentService.prepare(100, "USD", BigDecimal.valueOf(50.7))
    println("paymentService = $payment")
}