package jp.co.kopher1601.springadvancedkotlin.trace.strategy.code.strategy

import org.slf4j.LoggerFactory

class StrategyLogic2: Strategy {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun call() {
        log.info("비즈니스 로직2 실행")
    }
}