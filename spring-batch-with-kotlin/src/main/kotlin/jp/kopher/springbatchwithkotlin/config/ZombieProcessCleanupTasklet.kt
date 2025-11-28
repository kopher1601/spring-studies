package jp.kopher.springbatchwithkotlin.config

import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.StepContribution
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.infrastructure.repeat.RepeatStatus

class ZombieProcessCleanupTasklet: Tasklet {
    private val processesToKill = 10
    private var killedProcesses = 0
    private val log = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    override fun execute(
        contribution: StepContribution,
        chunkContext: ChunkContext
    ): RepeatStatus? {
        killedProcesses++;
        log.info("☠️  프로세스 강제 종료... ({}/{})", killedProcesses, processesToKill);

        if (killedProcesses >= processesToKill) {
            log.info("💀 시스템 안정화 완료. 모든 좀비 프로세스 제거.");
            return RepeatStatus.FINISHED;  // 모든 프로세스 종료 후 작업 완료
        }

        return RepeatStatus.CONTINUABLE;  // 아직 더 종료할 프로세스가 남아있음
    }
}