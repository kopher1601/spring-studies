package jp.co.kopher1601.springtxkotlin.propagation

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val logRepository: LogRepository,
) {

    val log = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun joinV1(username: String) {
        val member = Member(username)
        val logMessage = Log(username)

        log.info("== memberRepository 호출 시작 ==")
        memberRepository.save(member)
        log.info("== memberRepository 호출 종료 ==")

        log.info("== logRepository 호출 시작 ==")
        logRepository.save(logMessage)
        log.info("== logRepository 호출 종료 ==")

    }

    @Transactional
    fun joinV2(username: String) {
        val member = Member(username)
        val logMessage = Log(username)

        log.info("== memberRepository 호출 시작 ==")
        memberRepository.save(member)
        log.info("== memberRepository 호출 종료 ==")

        log.info("== logRepository 호출 시작 ==")
        try {
            logRepository.save(logMessage)
        } catch (e: RuntimeException) {
            log.info("log 저장에 실패했습니다. logMessage={}", logMessage.message)
            log.info("정상흐름 반환")
        }
        log.info("== logRepository 호출 종료 ==")

    }
}