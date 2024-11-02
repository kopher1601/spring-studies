package jp.co.kopher1601.springadvancedkotlin.trace.template.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractTemplate {

    protected val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun execute() {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        call() // 상속을 이용해서 푼다
        // 비즈니스 로직 종료

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime = {}", resultTime)
    }

    // 변하는 부분은 자식 클래스에서 상속을 받아 call 메소드를 구현해서 푼다
    protected abstract fun call()

}