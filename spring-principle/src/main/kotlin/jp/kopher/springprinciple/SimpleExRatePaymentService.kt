package jp.kopher.springprinciple

import java.math.BigDecimal

class SimpleExRatePaymentService: PaymentService() {
    override fun getExRate(currency: String): BigDecimal {
        if (currency == "USD") {
            return BigDecimal.valueOf(1000)
        }
        throw IllegalArgumentException("지원하지 않는 통화입니다.")
    }
}