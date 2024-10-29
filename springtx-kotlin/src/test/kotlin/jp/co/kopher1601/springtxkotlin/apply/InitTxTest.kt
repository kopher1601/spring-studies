package jp.co.kopher1601.springtxkotlin.apply

import jakarta.annotation.PostConstruct
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.event.EventListener
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@SpringBootTest
class InitTxTest @Autowired constructor(
    private val hello: Hello,
) {

    @Test
    fun go() {
        // 초기화 코드는 스프링이 초기화 시점에 호출한다. 직접 호출하지 말것
    }

    @TestConfiguration
    class InitTxTestConfig {

        @Bean
        fun hello(): Hello {
            return Hello()
        }
    }

    open class Hello {
        val log = LoggerFactory.getLogger(this.javaClass)

        @PostConstruct
        @Transactional
        open fun initV1() {
            val isActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("Hello init @PostConstruct tx active = {}", isActive) // false
        }

        @EventListener(ApplicationReadyEvent::class)
        @Transactional
        open fun initV2() {
            val isActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("Hello init @EventListener tx active = {}", isActive) //
        }
    }
}