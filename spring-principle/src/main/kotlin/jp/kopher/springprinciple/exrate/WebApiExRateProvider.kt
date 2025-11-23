package jp.kopher.springprinciple.exrate

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jp.kopher.springprinciple.payment.ExRateProvider
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.URL
import java.util.stream.Collectors

class WebApiExRateProvider: ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = URL(" https://open.er-api.com/v6/latest/${currency}")
        val connection = url.openConnection()
        val br = BufferedReader(InputStreamReader(connection.getInputStream(), "UTF-8"))
        val response = br.lines().collect(Collectors.joining())
        br.close()

        val mapper = jacksonObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)

        println("data.rates[\"KRW\"] = ${data.rates["KRW"]}")

        return data.rates["KRW"] ?: BigDecimal.ZERO
    }
}