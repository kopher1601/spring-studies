package jp.co.kopher1601.springadvancedkotlin.trace.strategy.template

import org.slf4j.LoggerFactory

class TimeLogTemplate {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute(callback: Callback) {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        callback.call() // 위임
        // 비즈니스 로직 종료

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime = {}", resultTime)
    }

}