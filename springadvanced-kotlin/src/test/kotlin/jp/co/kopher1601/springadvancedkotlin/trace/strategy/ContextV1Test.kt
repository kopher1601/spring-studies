package jp.co.kopher1601.springadvancedkotlin.trace.strategy

import jp.co.kopher1601.springadvancedkotlin.trace.strategy.code.strategy.ContextV1
import jp.co.kopher1601.springadvancedkotlin.trace.strategy.code.strategy.Strategy
import jp.co.kopher1601.springadvancedkotlin.trace.strategy.code.strategy.StrategyLogic1
import jp.co.kopher1601.springadvancedkotlin.trace.strategy.code.strategy.StrategyLogic2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ContextV1Test {

    val log = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun templateMethodV0() {
        logic1()
        logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행")
        // 비즈니스 로직 종료

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime = {}", resultTime)
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행")
        // 비즈니스 로직 종료

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime = {}", resultTime)
    }

    /**
     * 전략 패턴 사용
     */
    @Test
    fun strategyV1() {
        val strategyLogic1 = StrategyLogic1()
        val contextV1 = ContextV1(strategyLogic1)
        contextV1.execute()

        val strategyLogic2 = StrategyLogic2()
        val contextV2 = ContextV1(strategyLogic2)
        contextV2.execute()
    }

    @Test
    fun strategyV2() {
        val strategyLogic1 = object: Strategy {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        }

        val contextV1 = ContextV1(strategyLogic1)
        contextV1.execute()

        val strategyLogic2 = object: Strategy {
            override fun call() {
                log.info("비즈니스 로직2 실행")
            }
        }

        val contextV2 = ContextV1(strategyLogic2)
        contextV2.execute()
    }

    @Test
    fun strategyV3() {

        val contextV1 = ContextV1(object : Strategy {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        })
        contextV1.execute()

        val contextV2 = ContextV1(object : Strategy {
            override fun call() {
                log.info("비즈니스 로직2 실행")
            }
        })
        contextV2.execute()
    }
}