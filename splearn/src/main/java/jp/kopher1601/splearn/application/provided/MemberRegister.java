package jp.kopher1601.splearn.application.provided;

import jakarta.validation.Valid;
import jp.kopher1601.splearn.domain.Member;
import jp.kopher1601.splearn.domain.MemberRegisterRequest;

/**
 * 회원의 등록과 관련된 기능을 제공한다
 */
public interface MemberRegister {
    Member register(@Valid MemberRegisterRequest registerRequest);
}
