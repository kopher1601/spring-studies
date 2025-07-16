package jp.kopher1601.splearn.application.member;

import jp.kopher1601.splearn.application.member.provided.MemberFinder;
import jp.kopher1601.splearn.application.member.provided.MemberRegister;
import jp.kopher1601.splearn.application.member.required.EmailSender;
import jp.kopher1601.splearn.application.member.required.MemberRepository;
import jp.kopher1601.splearn.domain.member.DuplicateEmailException;
import jp.kopher1601.splearn.domain.member.Member;
import jp.kopher1601.splearn.domain.member.MemberRegisterRequest;
import jp.kopher1601.splearn.domain.member.PasswordEncoder;
import jp.kopher1601.splearn.domain.shared.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class MemberModifyService implements MemberRegister {
    private final MemberFinder memberFinder;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    // @Valid MemberRegisterRequest registerRequest 해도 되지만 interface에 붙여놓은 경우 생략해도 된다.
    @Override
    public Member register(MemberRegisterRequest registerRequest) {
        checkDuplicateEmail(registerRequest);

        Member member = Member.register(registerRequest, passwordEncoder);

        memberRepository.save(member);

        sendWelcomeEmail(member);

        return member;
    }

    @Override
    public Member activate(Long memberId) {
        Member member = memberFinder.find(memberId);
//        Member member = memberRepository
//                .findById(memberId)
//                // 람다가 실행되지 않는 한 문자열 연산이 이뤄지지 않는다
//                .orElseThrow(() -> new IllegalArgumentException("member not found. id : " + memberId));

        member.activate();

        // JPA 가 아니라 Spring Data JPA 를 사용하고 있기 때문에
        // Spring Data JPA 방식대로 사용해줘야 한다.
        // Update 상황에서도 save 를 호출해줘야 한다. (공식)
        //  - Repository Abstraction
        //  - Domain Event Publication
        //  - Auditing
        return memberRepository.save(member);
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
