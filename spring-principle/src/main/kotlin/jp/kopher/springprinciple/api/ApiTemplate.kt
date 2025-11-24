package jp.kopher.springprinciple.api

import java.math.BigDecimal
import java.net.URI

class ApiTemplate(
    private val apiExecutor: ApiExecutor,
    private val exRateExtractor: ExRateExtractor,
) {

    fun getForExRate(url: String): BigDecimal {
        return getForExRate(url, apiExecutor, exRateExtractor)
    }

    fun getForExRate(url: String, apiExecutor: ApiExecutor): BigDecimal {
        return this.getForExRate(url, apiExecutor, this.exRateExtractor)
    }

    fun getForExRate(url: String, exRateExtractor: ExRateExtractor): BigDecimal {
        return this.getForExRate(url, this.apiExecutor, exRateExtractor)
    }

    fun getForExRate(url: String, apiExecutor: ApiExecutor, exRateExtractor: ExRateExtractor): BigDecimal {
        val uri = URI(url)

        val response = apiExecutor.execute(uri)

        return exRateExtractor.extract(response)
    }
}