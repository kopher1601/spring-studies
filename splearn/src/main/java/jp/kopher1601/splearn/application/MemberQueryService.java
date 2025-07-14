package jp.kopher1601.splearn.application;

import jp.kopher1601.splearn.application.provided.MemberFinder;
import jp.kopher1601.splearn.application.required.MemberRepository;
import jp.kopher1601.splearn.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class MemberQueryService implements MemberFinder {

    private final MemberRepository memberRepository;

    @Override
    public Member find(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("member not found. id : " + memberId));
    }
}
