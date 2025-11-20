package jp.kopher.springprinciple

class ObjectFactory {
    fun paymentService(): PaymentService {
        return PaymentService(exRateProvider())
    }

    private fun exRateProvider(): ExRateProvider {
        return WebApiExRateProvider()
    }
}