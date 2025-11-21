package jp.kopher.springprinciple

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

class Client {
}

fun main() {
    val beanFactory = AnnotationConfigApplicationContext(ObjectFactory::class.java)
    val paymentService = beanFactory.getBean(PaymentService::class.java)

    val payment = paymentService.prepare(100, "USD", BigDecimal.valueOf(50.7))
    println("paymentService = $payment")
}