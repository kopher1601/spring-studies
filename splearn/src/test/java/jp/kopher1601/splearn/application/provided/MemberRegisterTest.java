package jp.kopher1601.splearn.application.provided;

import jp.kopher1601.splearn.SplearnTestConfiguration;
import jp.kopher1601.splearn.domain.DuplicateEmailException;
import jp.kopher1601.splearn.domain.Member;
import jp.kopher1601.splearn.domain.MemberFixture;
import jp.kopher1601.splearn.domain.MemberStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
public record MemberRegisterTest(
        MemberRegister memberRegister
) {

    @Test
    void register() {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void duplicateEmailFail() {
        Member member1 = memberRegister.register(MemberFixture.createMemberRegisterRequest());

        assertThatThrownBy(() -> memberRegister.register(MemberFixture.createMemberRegisterRequest()))
                .isInstanceOf(DuplicateEmailException.class);
    }

}
