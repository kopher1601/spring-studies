package jp.kopher1601.splearn.application.member.required;

import jp.kopher1601.splearn.domain.member.Member;
import jp.kopher1601.splearn.domain.member.Profile;
import jp.kopher1601.splearn.domain.shared.Email;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * 회원 정보를 저장하거나 조회한다
 */

// Repository -> Marker interface
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long memberId);

    // 1. join
    // 2. navigation
    @Query("select m from Member m where m.detail.profile = :profile")
    Optional<Member> findByProfile(Profile profile);
}
