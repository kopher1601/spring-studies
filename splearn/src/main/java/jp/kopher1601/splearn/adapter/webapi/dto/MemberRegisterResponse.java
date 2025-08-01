package jp.kopher1601.splearn.adapter.webapi.dto;

import jp.kopher1601.splearn.domain.member.Member;

public record MemberRegisterResponse(
        Long memberId,
        String email
) {
    public static MemberRegisterResponse of(Member member) {
        return new MemberRegisterResponse(
                member.getId(),
                member.getEmail().address()
        );
    }
}
