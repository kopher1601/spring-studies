package jp.kopher.springbatchwithkotlin.config

import org.springframework.batch.core.job.Job
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.Step
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class ZombieBatchConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
) {
    @Bean
    fun zombieProcessCleanupTasklet(): Tasklet {
        return ZombieProcessCleanupTasklet()
    }

    @Bean
    fun zombieCleanupStep(): Step {
        return StepBuilder("zombieCleanupStep", jobRepository)
            .tasklet(zombieProcessCleanupTasklet(), transactionManager)
            .build()
    }

    @Bean
    fun zombieCleanupJob(): Job {
        return JobBuilder("zombieCleanupJob", jobRepository)
            .start(zombieCleanupStep())
            .build()
    }

}