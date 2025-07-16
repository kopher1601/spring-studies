package jp.kopher1601.splearn.application.member.required;

import jp.kopher1601.splearn.domain.member.Member;
import jp.kopher1601.splearn.domain.shared.Email;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * 회원 정보를 저장하거나 조회한다
 */

// Repository -> Marker interface
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long memberid);
}
