package jp.kopher1601.splearn.application.provided;

import jp.kopher1601.splearn.domain.Member;

/**
 * 회원을 조회한다
 */
public interface MemberFinder {
    Member find(Long memberId);
}
