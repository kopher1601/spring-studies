package jp.kopher1601.splearn.application;

import jp.kopher1601.splearn.application.provided.MemberRegister;
import jp.kopher1601.splearn.application.required.EmailSender;
import jp.kopher1601.splearn.application.required.MemberRepository;
import jp.kopher1601.splearn.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class MemberService implements MemberRegister {

    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    // @Valid MemberRegisterRequest registerRequest 해도 되지만 interface에 붙여놓은 경우 생략해도 된다.
    @Override
    public Member register(MemberRegisterRequest registerRequest) {
        checkDuplicateEmail(registerRequest);

        Member member = Member.register(registerRequest, passwordEncoder);

        memberRepository.save(member);

        sendWelcomeEmail(member);

        return member;
    }

    private void sendWelcomeEmail(Member member) {
        emailSender.send(member.getEmail(), "登録を完了してください", "下のリンクをクリックして登録を完了してください");
    }

    private void checkDuplicateEmail(MemberRegisterRequest registerRequest) {
        if (memberRepository.findByEmail(new Email(registerRequest.email())).isPresent()) {
            throw new DuplicateEmailException("email is already registered");
        }
    }

}
