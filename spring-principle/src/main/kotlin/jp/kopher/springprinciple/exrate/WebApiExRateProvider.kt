package jp.kopher.springprinciple.exrate

import jp.kopher.springprinciple.api.ApiTemplate
import jp.kopher.springprinciple.api.ErApiExRateExtractor
import jp.kopher.springprinciple.api.HttpClientApiExecutor
import jp.kopher.springprinciple.payment.ExRateProvider
import java.math.BigDecimal

class WebApiExRateProvider : ExRateProvider {

    private val apiTemplate = ApiTemplate()

    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/${currency}"

        return apiTemplate.getExRate(url, HttpClientApiExecutor(), ErApiExRateExtractor())
    }
}