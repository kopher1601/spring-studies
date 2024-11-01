package jp.co.kopher1601.springadvancedkotlin.trace.hellotrace

import jp.co.kopher1601.springadvancedkotlin.trace.TraceId
import jp.co.kopher1601.springadvancedkotlin.trace.TraceStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class HelloTraceV1 {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val START_PREFIX: String = "-->"
    private val COMPLETE_PREFIX: String = "<--"
    private val EX_PREFIX: String = "<X-"

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()

        // 로그 출력
        log.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message);
        return TraceStatus(traceId, startTimeMs, message)
    }

    fun end(status: TraceStatus) {
        complete(status, null)
    }

    fun exception(status: TraceStatus, e: Exception) {
        complete(status, e);
    }

    fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startTimeMs
        val traceId = status.traceId
        log.info(
            "[{}] {}{} time={}ms ex={}", traceId.id,
            addSpace(EX_PREFIX, traceId.level), status.message, resultTimeMs,
            e.toString()
        )
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(if ((i == level - 1)) "|$prefix" else "| ")
        }
        return sb.toString()
    }

}