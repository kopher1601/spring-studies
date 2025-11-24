package jp.kopher.springprinciple.exrate

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jp.kopher.springprinciple.api.SimpleApiExecutor
import jp.kopher.springprinciple.payment.ExRateProvider
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.URI
import java.util.stream.Collectors

class WebApiExRateProvider: ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/${currency}"

        return runApiForExRate(url)
    }

    private fun runApiForExRate(url: String): BigDecimal {
        val uri = URI(url)

        val response = SimpleApiExecutor().execute(uri)

        return extractExRate(response)
    }

    private fun extractExRate(response: String): BigDecimal {
        val mapper = jacksonObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)

        return data.rates["KRW"] ?: BigDecimal.ZERO
    }

}