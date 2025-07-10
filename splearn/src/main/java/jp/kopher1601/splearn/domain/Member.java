package jp.kopher1601.splearn.domain;

import lombok.Getter;
import lombok.ToString;

import static org.springframework.util.Assert.state;

import java.util.Objects;

@Getter
@ToString
public class Member {
    private String email;
    private String nickname;
    private String passwordHash;
    private MemberStatus status;
    
    private Member(String email, String nickname, String passwordHash) {
        this.email = Objects.requireNonNull(email);
        this.nickname = Objects.requireNonNull(nickname);
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.status = MemberStatus.PENDING;
    }

    public static Member create(String email, String nickname, String password, PasswordEncoder passwordEncoder) {
        return new Member(email, nickname, passwordEncoder.encode(password));
    }

    public void activate() {
        state(this.status == MemberStatus.PENDING, "Member is not pending");

        this.status = MemberStatus.ACTIVE;
    }

    public void deactivate() {
        state(this.status == MemberStatus.ACTIVE, "Member is not active");

        this.status = MemberStatus.DEACTIVATED;
    }

    public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.passwordHash);
    }
}
