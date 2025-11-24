package jp.kopher.springprinciple.exrate

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jp.kopher.springprinciple.payment.ExRateProvider
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.URI
import java.net.URL
import java.util.stream.Collectors

class WebApiExRateProvider: ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val uri = URI(" https://open.er-api.com/v6/latest/${currency}")

        val response = executeApi(uri)

        return parseExRate(response)
    }

    private fun parseExRate(response: String): BigDecimal {
        val mapper = jacksonObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)

        return data.rates["KRW"] ?: BigDecimal.ZERO
    }

    private fun executeApi(uri: URI): String {
        val connection = uri.toURL().openConnection()
        return BufferedReader(InputStreamReader(connection.getInputStream(), "UTF-8")).use {
            it.lines().collect(Collectors.joining())
        }
    }
}