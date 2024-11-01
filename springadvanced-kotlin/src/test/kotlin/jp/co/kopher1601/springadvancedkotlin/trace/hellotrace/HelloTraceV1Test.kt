package jp.co.kopher1601.springadvancedkotlin.trace.hellotrace

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HelloTraceV1Test{

    @Test
    fun begin_end() {
        val trace = HelloTraceV1()
        val status = trace.begin("hello")
        trace.end(status)
    }

    @Test
    fun begin_exception() {
        val trace = HelloTraceV1()
        val status = trace.begin("hello")
        trace.exception(status, IllegalStateException())
    }
}