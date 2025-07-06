package jp.kopher1601.splearn.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void createMember() {
        var member = new Member("test@example.com", "test", "password");

        assertThat(member.getEmail()).isEqualTo("test@example.com");
        assertThat(member.getNickname()).isEqualTo("test");
        assertThat(member.getPasswordHash()).isEqualTo("password");
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }
}
