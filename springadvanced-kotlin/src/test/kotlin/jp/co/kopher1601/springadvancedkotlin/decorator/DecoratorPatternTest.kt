package jp.co.kopher1601.springadvancedkotlin.decorator

import jp.co.kopher1601.springadvancedkotlin.decorator.code.DecoratorPatternClient
import jp.co.kopher1601.springadvancedkotlin.decorator.code.MessageDecorator
import jp.co.kopher1601.springadvancedkotlin.decorator.code.RealComponent
import jp.co.kopher1601.springadvancedkotlin.decorator.code.TimeDecorator
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class DecoratorPatternTest {

    private val log = LoggerFactory.getLogger(DecoratorPatternTest::class.java)

    @Test
    fun noDecorator() {
        val realComponent = RealComponent()
        val client = DecoratorPatternClient(realComponent)
        client.execute()
    }

    @Test
    fun decorator1() {
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        val client = DecoratorPatternClient(messageDecorator)
        client.execute()
    }

    @Test
    fun decorator2() {
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        val timeDecorator = TimeDecorator(messageDecorator)
        val client = DecoratorPatternClient(timeDecorator)
        client.execute()
    }
}