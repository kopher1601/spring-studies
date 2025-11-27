package jp.kopher.springbatchwithkotlin.config

import org.springframework.batch.core.job.Job
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.Step
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.infrastructure.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import java.util.concurrent.atomic.AtomicInteger


@Configuration
class SystemTerminationConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
) {

    private val processesKilled = AtomicInteger(0)
    private val TERMINATION_TARGET = 5


    @Bean
    fun systemTerminationSimulationJob(): Job {
        return JobBuilder("systemTerminationSimulationJob", jobRepository)
            .start(enterWorldStep())
            .next(meetNPCStep())
            .next(defeatProcessStep())
            .next(completeQuestStep())
            .build()
    }

    @Bean
    fun enterWorldStep(): Step {
        return StepBuilder("enterWorldStep", jobRepository)
            .tasklet({ contribution, chunkContext ->
                println("welcome to spring batch")
                RepeatStatus.FINISHED
            }, transactionManager)
            .build()
    }

    @Bean
    fun meetNPCStep(): Step {
        return StepBuilder("meetNPCStep", jobRepository)
            .tasklet({ contribution, chunkContext ->
                println("Meet NPC")
                println("First Mission $TERMINATION_TARGET")
                RepeatStatus.FINISHED
            }, transactionManager)
            .build()
    }

    @Bean
    fun defeatProcessStep(): Step? {
        return StepBuilder("defeatProcessStep", jobRepository)
            .tasklet({ contribution, chunkContext ->
                val terminated: Int = processesKilled.incrementAndGet()
                println("좀비 프로세스 처형 완료! (현재 $terminated/$TERMINATION_TARGET)")
                if (terminated < TERMINATION_TARGET) {
                    return@tasklet RepeatStatus.CONTINUABLE
                } else {
                    return@tasklet RepeatStatus.FINISHED
                }
            }, transactionManager)
            .build()
    }

    @Bean
    fun completeQuestStep(): Step? {
        return StepBuilder("completeQuestStep", jobRepository)
            .tasklet({ contribution, chunkContext ->
                println("미션 완료! 좀비 프로세스 " + TERMINATION_TARGET + "개 처형 성공!")
                println("보상: kill -9 권한 획득, 시스템 제어 레벨 1 달성")
                RepeatStatus.FINISHED
            }, transactionManager)
            .build()
    }


}