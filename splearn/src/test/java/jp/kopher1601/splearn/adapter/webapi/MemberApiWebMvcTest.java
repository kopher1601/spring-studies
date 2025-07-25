package jp.kopher1601.splearn.adapter.webapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.kopher1601.splearn.application.member.provided.MemberRegister;
import jp.kopher1601.splearn.domain.member.Member;
import jp.kopher1601.splearn.domain.member.MemberFixture;
import jp.kopher1601.splearn.domain.member.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(MemberApi.class) // @WebMvcTest 는 하든 안하든 선택, 결국 Mock 을 사용하기 때문에 정확한 테스트가 어렵다
@RequiredArgsConstructor
class MemberApiWebMvcTest {

    final MockMvcTester mvcTester;
    final ObjectMapper objectMapper;
    // Spring 6.2 부터 MockMvcTester 를 사용할 수 있음
    // AssertJ 와 접목이 되어 있음
    @MockitoBean
    private MemberRegister memberRegister;

    @Test
    void register() throws JsonProcessingException {
        Member member = MemberFixture.createMember(1L);
        when(memberRegister.register(any())).thenReturn(member);

        MemberRegisterRequest request = MemberFixture.createMemberRegisterRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        assertThat(
                mvcTester.post()
                        .uri("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).hasStatusOk()
                .bodyJson()
                .extractingPath("$.memberId").asNumber().isEqualTo(1);

        verify(memberRegister).register(request);
    }

    @Test
    void registerFail() throws JsonProcessingException {
        MemberRegisterRequest request = MemberFixture.createMemberRegisterRequest("invalid email");
        String requestJson = objectMapper.writeValueAsString(request);

        assertThat(mvcTester.post()
                .uri("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        ).hasStatus(HttpStatus.BAD_REQUEST);
    }
}