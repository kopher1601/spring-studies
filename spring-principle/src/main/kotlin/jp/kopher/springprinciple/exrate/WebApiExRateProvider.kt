package jp.kopher.springprinciple.exrate

import jp.kopher.springprinciple.api.ApiExecutor
import jp.kopher.springprinciple.api.ErApiExRateExtractor
import jp.kopher.springprinciple.api.ExRateExtractor
import jp.kopher.springprinciple.api.SimpleApiExecutor
import jp.kopher.springprinciple.payment.ExRateProvider
import java.math.BigDecimal
import java.net.URI

class WebApiExRateProvider: ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/${currency}"

        return runApiForExRate(url, SimpleApiExecutor(), ErApiExRateExtractor())
    }

    private fun runApiForExRate(url: String, apiExecutor: ApiExecutor, exRateExtractor: ExRateExtractor): BigDecimal {
        val uri = URI(url)

        val response = apiExecutor.execute(uri)

        return exRateExtractor.extract(response)
    }
}