package jp.kopher.springprinciple.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jp.kopher.springprinciple.exrate.ExRateData
import java.math.BigDecimal

class ErApiExRateExtractor: ExRateExtractor {
    override fun extract(response: String): BigDecimal {
        val mapper = jacksonObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)

        return data.rates["KRW"] ?: BigDecimal.ZERO
    }
}