package jp.kopher.springprinciple

class PaymentService {
    fun prepare(): Payment {
        return Payment()
    }
}

fun main() {
    val paymentService = PaymentService()
    val payment = paymentService.prepare()
    println("paymentService = $payment")
}