package jp.co.kopher1601.springadvancedkotlin.decorator.code

import org.slf4j.LoggerFactory

class MessageDecorator(
    private val component: Component,
): Component {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun operation(): String {
        log.info("MessageDecorator 실행")
        val operation = component.operation()
        val decoResult = "***** $operation *****"
        log.info("MessageDecorator 꾸미기 적용 전 = {}, 적용 후 = {}", operation, decoResult)
        return decoResult
    }
}