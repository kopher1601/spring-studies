package jp.kopher1601.splearn.application.member.provided;

import jakarta.validation.Valid;
import jp.kopher1601.splearn.domain.member.Member;
import jp.kopher1601.splearn.domain.member.MemberInfoUpdateRequest;
import jp.kopher1601.splearn.domain.member.MemberRegisterRequest;

/**
 * 회원의 등록과 관련된 기능을 제공한다
 */
public interface MemberRegister {
    Member register(@Valid MemberRegisterRequest registerRequest);

    Member activate(Long memberId);

    Member deactivate(Long memberId);

    Member updateInfo(Long memberId, @Valid MemberInfoUpdateRequest updateRequest);
}
