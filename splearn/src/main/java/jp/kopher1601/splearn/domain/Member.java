package jp.kopher1601.splearn.domain;

import lombok.Getter;
import lombok.ToString;

import static java.util.Objects.requireNonNull;
import static org.springframework.util.Assert.state;

@Getter
@ToString
public class Member {
    private String email;
    private String nickname;
    private String passwordHash;
    private MemberStatus status;
    
    private Member() {
    }

    public static Member create(MembmerCreateRequest createRequest, PasswordEncoder passwordEncoder) {
        var member = new Member();

        member.email = requireNonNull(createRequest.email());
        member.nickname = requireNonNull(createRequest.nickname());
        member.passwordHash = requireNonNull(passwordEncoder.encode(createRequest.password()));
        member.status = MemberStatus.PENDING;

        return member;
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

    public void changeNickname(String nickname) {
        this.nickname = requireNonNull(nickname);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.passwordHash = passwordEncoder.encode(requireNonNull(password));
    }

    public boolean isActive() {
        return this.status == MemberStatus.ACTIVE;
    }
}
