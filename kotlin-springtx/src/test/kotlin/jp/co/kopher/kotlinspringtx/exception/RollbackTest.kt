package jp.co.kopher.kotlinspringtx.exception

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RollbackTest(
    private val rollbackService: RollbackService,
) {

    @Test
    fun runtimeException() {
        assertThatThrownBy {
            rollbackService.runtimeException()
        }.isInstanceOf(RuntimeException::class.java)
    }

    @Test
    fun checkedException() {
        assertThatThrownBy {
            rollbackService.checkedException()
        }.isInstanceOf(MyException::class.java)
    }

    @Test
    fun rollbackFor() {
        assertThatThrownBy {
            rollbackService.rollbackFor()
        }.isInstanceOf(MyException::class.java)
    }

    @TestConfiguration
    class RollbackTestConfig {
        @Bean
        fun rollbackService(): RollbackService {
            return RollbackService()
        }
    }

    open class RollbackService {
        val log: Logger = LoggerFactory.getLogger(this::class.java)

        // 런다팀 예외 발생 : Rollback
        @Transactional
        open fun runtimeException() {
            log.info("call runtimeException")
            throw RuntimeException()
        }

        // 체크 예외 발생 : Commit
        @Transactional
        open fun checkedException() {
            log.info("call checkedException")
            throw MyException()
        }

        // 체크 예외 rollbackFrom 지정 : Rollback
        @Transactional(rollbackFor = [MyException::class])
        open fun rollbackFor() {
            log.info("call rollbackFor")
            throw MyException()
        }
    }

    class MyException : Exception()
}