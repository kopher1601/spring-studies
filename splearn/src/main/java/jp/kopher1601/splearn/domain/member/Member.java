package jp.kopher1601.splearn.domain.member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jp.kopher1601.splearn.domain.AbstractEntity;
import jp.kopher1601.splearn.domain.shared.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.springframework.util.Assert.state;

@Entity
@Getter
@ToString(callSuper = true, exclude = "detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends AbstractEntity {

    @NaturalId
    private Email email;

    private String nickname;

    private String passwordHash;

    /**
     * Database 가 Enum 타입을 지원한다면 Enum 타입으로 아니면 varchar 타입으로 설정해준다.
     */
    private MemberStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MemberDetail detail;

    public static Member register(MemberRegisterRequest createRequest, PasswordEncoder passwordEncoder) {
        var member = new Member();

        member.email = new Email(createRequest.email());
        member.nickname = requireNonNull(createRequest.nickname());
        member.passwordHash = requireNonNull(passwordEncoder.encode(createRequest.password()));
        member.status = MemberStatus.PENDING;

        member.detail = MemberDetail.create();

        return member;
    }

    public void activate() {
        state(this.status == MemberStatus.PENDING, "Member is not pending");

        this.status = MemberStatus.ACTIVE;
        this.detail.setActivatedAt();
    }

    public void deactivate() {
        state(this.status == MemberStatus.ACTIVE, "Member is not active");

        this.status = MemberStatus.DEACTIVATED;
        this.detail.deactivate();
    }

    public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.passwordHash);
    }

    public void changeNickname(String nickname) {
        this.nickname = requireNonNull(nickname);
    }

    public void updateInfo(MemberInfoUpdateRequest updateRequest) {
        this.nickname = Objects.requireNonNull(updateRequest.nickname());

        this.detail.updateInfo(updateRequest);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.passwordHash = passwordEncoder.encode(requireNonNull(password));
    }

    public boolean isActive() {
        return this.status == MemberStatus.ACTIVE;
    }
}
