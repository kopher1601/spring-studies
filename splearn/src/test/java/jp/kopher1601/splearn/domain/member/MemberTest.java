package jp.kopher1601.splearn.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static jp.kopher1601.splearn.domain.member.MemberFixture.createMemberRegisterRequest;
import static jp.kopher1601.splearn.domain.member.MemberFixture.createPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = createPasswordEncoder();

        member = Member.register(createMemberRegisterRequest(), passwordEncoder);
    }

    @Test
    void registerMember() {
        assertThat(member.getEmail().address()).isEqualTo("test@example.com");
        assertThat(member.getNickname()).isEqualTo("test_member");
        assertThat(member.getPasswordHash()).isEqualTo("PASSWORDPASSWORD");
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);

        assertThat(member.getDetail().getRegisteredAt()).isNotNull();
    }

    @Test
    void activate() {
        member.activate();
        
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(member.getDetail().getActivatedAt()).isNotNull();
    }

    @Test
    void activateFail() {
        member.activate();

        assertThatThrownBy(() -> member.activate())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Member is not pending");
    }

    @Test
    void deactivate() {
        member.activate();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
        assertThat(member.getDetail().getDeactivatedAt()).isNotNull();
    }

    @Test
    void deactivateFail() {
        assertThatThrownBy(() -> member.deactivate())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Member is not active");

        member.activate();
        member.deactivate();

        assertThatThrownBy(() -> member.deactivate())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Member is not active");
    }

    @Test
    void verifyPassword() {
        assertThat(member.verifyPassword("passwordpassword", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("Hello", passwordEncoder)).isFalse();
    }

    @Test
    void changeNickname() {
        assertThat(member.getNickname()).isEqualTo("test_member");

        member.changeNickname("Kopher1601");

        assertThat(member.getNickname()).isEqualTo("Kopher1601");
    }

    @Test
    void changePassword() {
        member.changePassword("verysecret", passwordEncoder);
        
        assertThat(member.verifyPassword("verysecret", passwordEncoder)).isTrue();
    }

    @Test
    void shouldBeActive() {
        assertThat(member.isActive()).isFalse();

        member.activate();

        assertThat(member.isActive()).isTrue();

        member.deactivate();

        assertThat(member.isActive()).isFalse();
    }

    @Test
    void invalidEmail() {
        assertThatThrownBy(() -> Member.register(createMemberRegisterRequest("invalid"), passwordEncoder))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid email address: invalid");
    }

    @Test
    void updateInfo() {
        member.activate();

        MemberInfoUpdateRequest request = new MemberInfoUpdateRequest("Leo", "kopher1601", "자기소개");
        member.updateInfo(request);

        assertThat(member.getNickname()).isEqualTo(request.nickname());
        assertThat(member.getDetail().getProfile().address()).isEqualTo(request.profileAddress());
        assertThat(member.getDetail().getIntroduction()).isEqualTo(request.introduction());
    }

    @Test
    void updateInfoFail() {
        assertThatThrownBy(() -> {
            var request = new MemberInfoUpdateRequest("Leo", "kopher1601", "자기소개");
            member.updateInfo(request);
        })
                .isInstanceOf(IllegalStateException.class);
    }
}
