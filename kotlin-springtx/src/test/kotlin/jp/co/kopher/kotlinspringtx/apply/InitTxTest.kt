package jp.co.kopher.kotlinspringtx.apply

import jakarta.annotation.PostConstruct
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.event.EventListener
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class InitTxTest(
    private val hello: Hello
) {

    @Test
    fun go() {
        // 초기화 코드는 스프링이 초기화 시점에 호출한다
    }


    @TestConfiguration
    class InitTxTestConfig {

        @Bean
        fun hello(): Hello {
            return Hello()
        }
    }

    open class Hello {

        val log: Logger = LoggerFactory.getLogger(this.javaClass)

        @PostConstruct
        @Transactional
        open fun initV1() {
            // @PostConstruct 가 먼저 실행되고 @Transactional 이 적용되기 때문에 여기에는 Transactional 이 적용되지 않는다
            val isActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("hello init @PostConstruct tx active = {}", isActive)
        }

        // 스프링 컨테이너가 완전히 떳을 때 호출된다
        @EventListener(ApplicationReadyEvent::class)
        @Transactional
        open fun initV2() {
            val isActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("hello init ApplicationReadyEvent tx active = {}", isActive)
        }
    }
}