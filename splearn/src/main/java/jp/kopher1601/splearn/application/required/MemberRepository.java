package jp.kopher1601.splearn.application.required;

import jp.kopher1601.splearn.domain.Email;
import jp.kopher1601.splearn.domain.Member;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * 회원 정보를 저장하거나 조회한다
 */

// Repository -> Marker interface
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);

    Optional<Member> findByEmail(Email email);
}
