package jp.kopher1601.splearn.application;

import jp.kopher1601.splearn.application.provided.MemberRegister;
import jp.kopher1601.splearn.application.required.EmailSender;
import jp.kopher1601.splearn.application.required.MemberRepository;
import jp.kopher1601.splearn.domain.Member;
import jp.kopher1601.splearn.domain.MemberRegisterRequest;
import jp.kopher1601.splearn.domain.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberRegister {

    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member register(MemberRegisterRequest registerRequest) {
        // check

        // domain model
        Member member = Member.register(registerRequest, passwordEncoder);

        // repository
        memberRepository.save(member);

        // post process
        emailSender.send(member.getEmail(), "登録を完了してください", "下のリンクをクリックして登録を完了してください");

        return member;
    }

}
