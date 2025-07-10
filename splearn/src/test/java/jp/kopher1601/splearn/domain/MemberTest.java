package jp.kopher1601.splearn.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };

        member = Member.create("test@example.com", "test", "password", passwordEncoder);
    }
    
    @Test
    void createMember() {
        assertThat(member.getEmail()).isEqualTo("test@example.com");
        assertThat(member.getNickname()).isEqualTo("test");
        assertThat(member.getPasswordHash()).isEqualTo("PASSWORD");
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void activate() {
        member.activate();
        
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
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
        assertThat(member.verifyPassword("password", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("Hello", passwordEncoder)).isFalse();
    }

    @Test
    void changeNickname() {
        assertThat(member.getNickname()).isEqualTo("test");

        member.changeNickname("Kopher1601");

        assertThat(member.getNickname()).isEqualTo("Kopher1601");
    }

    @Test
    void changePassword() {
        member.changePassword("verysecret", passwordEncoder);
        
        assertThat(member.verifyPassword("verysecret", passwordEncoder)).isTrue();
    }
}
