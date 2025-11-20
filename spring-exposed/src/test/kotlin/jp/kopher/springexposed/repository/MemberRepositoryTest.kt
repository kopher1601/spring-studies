package jp.kopher.springexposed.repository

import jp.kopher.springexposed.domain.Member
import jp.kopher.springexposed.domain.Members
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MemberRepositoryTest(
    private val memberRepository: MemberRepository,
) {

    @Test
    fun `会員を保存して ID を返す`() {
        val member = Member(
            name = "Exposed",
            age = 2,
        )

        val resultRows = memberRepository.save(member)

        assertThat(resultRows[Members.id]).isNotNull()
        assertThat(resultRows[Members.name]).isEqualTo("Exposed")
        assertThat(resultRows[Members.age]).isEqualTo(2)
    }
}