package jp.co.kopher1601.springadvancedkotlin.decorator.code

import org.slf4j.LoggerFactory

class RealComponent: Component {

    private val log = LoggerFactory.getLogger(RealComponent::class.java)

    override fun operation(): String {
        log.info("RealComponent 실행")
        return "data"
    }
}