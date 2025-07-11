package jp.kopher1601.splearn.application.required;

import jakarta.persistence.EntityManager;
import jp.kopher1601.splearn.domain.Member;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import static jp.kopher1601.splearn.domain.MemberFixture.createMemberRegisterRequest;
import static jp.kopher1601.splearn.domain.MemberFixture.createPasswordEncoder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class MemberRepositoryTest {

    private final MemberRepository memberRepository;
    private final EntityManager entityManager;

    @Test
    void createMember() {
        Member member = Member.register(createMemberRegisterRequest(), createPasswordEncoder());

        assertThat(member.getId()).isNull();

        memberRepository.save(member);

        assertThat(member.getId()).isNotNull();

        entityManager.flush();
    }
}