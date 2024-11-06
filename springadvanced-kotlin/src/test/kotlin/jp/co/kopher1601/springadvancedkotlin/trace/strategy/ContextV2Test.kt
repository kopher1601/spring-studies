package jp.co.kopher1601.springadvancedkotlin.trace.strategy

import jp.co.kopher1601.springadvancedkotlin.trace.strategy.code.strategy.ContextV2
import jp.co.kopher1601.springadvancedkotlin.trace.strategy.code.strategy.StrategyLogic1
import jp.co.kopher1601.springadvancedkotlin.trace.strategy.code.strategy.StrategyLogic2
import org.junit.jupiter.api.Test

class ContextV2Test {

    /**
     * 전략 패턴 적용
     */
    @Test
    fun strategyV1() {
        val context = ContextV2()
        context.execute(StrategyLogic1())
        context.execute(StrategyLogic2())
    }
}