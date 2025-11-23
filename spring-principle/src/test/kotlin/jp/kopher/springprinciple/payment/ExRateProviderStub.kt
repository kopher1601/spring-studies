package jp.kopher.springprinciple.payment

import java.math.BigDecimal

class ExRateProviderStub(
    private var exRate: BigDecimal? = null
): ExRateProvider {

    override fun getExRate(currency: String): BigDecimal {
        return exRate ?: BigDecimal.valueOf(1000)
    }
}