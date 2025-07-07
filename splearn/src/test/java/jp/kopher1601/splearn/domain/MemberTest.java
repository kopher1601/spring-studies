package jp.kopher1601.splearn.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    @Test
    void createMember() {
        var member = new Member("test@example.com", "test", "password");

        assertThat(member.getEmail()).isEqualTo("test@example.com");
        assertThat(member.getNickname()).isEqualTo("test");
        assertThat(member.getPasswordHash()).isEqualTo("password");
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void constructorNullCheck() {
        assertThatThrownBy(() -> new Member(null, "test", "password"))
            .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Member("test@example.com", null, "password"))
            .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Member("test@example.com", "test", null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void activate() {
        var member = new Member("test@example.com", "test", "password");
        
        member.activate();
        
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void activateFail() {
        var member = new Member("test@example.com", "test", "password");
        member.activate();

        assertThatThrownBy(() -> member.activate())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Member is not pending");
    }

    @Test
    void deactivate() {
        var member = new Member("test@example.com", "test", "password");
        member.activate();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void deactivateFail() {
        var member = new Member("test@example.com", "test", "password");

        assertThatThrownBy(() -> member.deactivate())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Member is not active");

        member.activate();
        member.deactivate();

        assertThatThrownBy(() -> member.deactivate())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Member is not active");
    }
}
