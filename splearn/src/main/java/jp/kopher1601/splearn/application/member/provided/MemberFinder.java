package jp.kopher1601.splearn.application.member.provided;

import jp.kopher1601.splearn.domain.member.Member;

/**
 * 회원을 조회한다
 */
public interface MemberFinder {
    Member find(Long memberId);
}
