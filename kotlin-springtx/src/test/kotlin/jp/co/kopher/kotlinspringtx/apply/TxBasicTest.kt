package jp.co.kopher.kotlinspringtx.apply

import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.support.AopUtils
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.support.TransactionSynchronizationManager

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TxBasicTest(
    private val basicService: BasicService
) {

    val log: Logger = LoggerFactory.getLogger(TxBasicTest::class.java)

    @Test
    fun proxyCheck() {
        log.info("aop class = {}", basicService.javaClass)
        assertThat(AopUtils.isAopProxy(basicService)).isTrue()
    }

    @Test
    fun txTest() {
        basicService.tx()
        basicService.nonTx()
    }

    @TestConfiguration
    class TxApplyBasicConfig {
        val log: Logger = LoggerFactory.getLogger(TxApplyBasicConfig::class.java)

        @Bean
        fun basicService(): BasicService {
            return BasicService()
        }
    }

    open class BasicService {
        companion object {
            val log: Logger = LoggerFactory.getLogger(BasicService::class.java)
        }

        @Transactional
        open fun tx() {
            log.info("call tx")
            val txActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("tx active = {}", txActive)
        }

        fun nonTx() {
            log.info("call nonTx")
            val txActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("tx active = {}", txActive)
        }
    }
}