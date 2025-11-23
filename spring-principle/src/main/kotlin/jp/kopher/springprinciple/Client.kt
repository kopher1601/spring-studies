package jp.kopher.springprinciple

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

class Client {
}

fun main() {
    val beanFactory = AnnotationConfigApplicationContext(ObjectFactory::class.java)
    val paymentService = beanFactory.getBean(PaymentService::class.java)

    val payment1 = paymentService.prepare(100, "USD", BigDecimal.valueOf(50.7))
    println("paymentService1 = $payment1")
    println("---------------------------------------")

    val payment2 = paymentService.prepare(100, "USD", BigDecimal.valueOf(50.7))
    println("paymentService2 = $payment2")
    println("---------------------------------------")

    TimeUnit.SECONDS.sleep(3L)

    val payment3 = paymentService.prepare(100, "USD", BigDecimal.valueOf(50.7))
    println("paymentService3 = $payment3")
}