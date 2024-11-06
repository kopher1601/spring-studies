package jp.co.kopher1601.springadvancedkotlin.trace.strategy

import jp.co.kopher1601.springadvancedkotlin.trace.strategy.template.Callback
import jp.co.kopher1601.springadvancedkotlin.trace.strategy.template.TimeLogTemplate
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class TemplateCallbackTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * 탬플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    fun callbackV1() {
        val template = TimeLogTemplate()
        template.execute(object : Callback {
            override fun call() {
                log.info("비즈니스 로직 1 실행")
            }
        })
    }
}