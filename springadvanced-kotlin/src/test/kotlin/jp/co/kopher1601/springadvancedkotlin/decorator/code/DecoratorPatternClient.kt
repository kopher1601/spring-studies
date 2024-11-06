package jp.co.kopher1601.springadvancedkotlin.decorator.code

import org.slf4j.LoggerFactory

class DecoratorPatternClient(
    private val component: Component,
) {

    private val log = LoggerFactory.getLogger(DecoratorPatternClient::class.java)

    fun execute() {
        val result = component.operation()

        log.info("result = {} ", result)
    }
}