package jp.co.kopher1601.springtxkotlin.propagation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
    private val logRepository: LogRepository,
) {

    /**
     * memberService        @Transactional: OFF
     * memberRepository     @Transactional: ON
     * logRepository        @Transactional: ON
     */
    @Test
    fun outerTxOff_success() {
        // given
        val username = "outerTxOff_success"

        // when
        memberService.joinV1(username)

        // then
        assertThat(memberRepository.find(username)).isNotNull
        assertThat(logRepository.find(username)).isNotNull

    }
}