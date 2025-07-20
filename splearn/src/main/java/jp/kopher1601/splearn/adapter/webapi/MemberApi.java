package jp.kopher1601.splearn.adapter.webapi;

import jakarta.validation.Valid;
import jp.kopher1601.splearn.adapter.webapi.dto.MemberRegisterResponse;
import jp.kopher1601.splearn.application.member.provided.MemberRegister;
import jp.kopher1601.splearn.domain.member.Member;
import jp.kopher1601.splearn.domain.member.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberRegister memberRegister;

    // register api -> POST /members
    @PostMapping("/api/members")
    public MemberRegisterResponse register(
            // @Validated 를 클래스 레벨에 붙이지 않아도 MVC 를 사용한다면 알아서 검증해준다
            @RequestBody @Valid MemberRegisterRequest request
    ) {
        Member member = memberRegister.register(request);

        return MemberRegisterResponse.of(member);
    }
}
