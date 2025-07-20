package jp.kopher1601.splearn.adapter.webapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.kopher1601.splearn.adapter.webapi.dto.MemberRegisterResponse;
import jp.kopher1601.splearn.application.member.provided.MemberRegister;
import jp.kopher1601.splearn.application.member.required.MemberRepository;
import jp.kopher1601.splearn.domain.member.Member;
import jp.kopher1601.splearn.domain.member.MemberFixture;
import jp.kopher1601.splearn.domain.member.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

import static jp.kopher1601.splearn.AssertThatUtils.equalsTo;
import static jp.kopher1601.splearn.AssertThatUtils.notNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
public class MemberApiTest {

    final MockMvcTester mvcTester;
    final ObjectMapper objectMapper;
    final MemberRepository memberRepository;
    final MemberRegister memberRegister;

    @Test
    void register() throws JsonProcessingException, UnsupportedEncodingException {
        MemberRegisterRequest request = MemberFixture.createMemberRegisterRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        MvcTestResult result = mvcTester.post()
                .uri("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .exchange();

        assertThat(result)
                .hasStatusOk()
                .bodyJson()
                .hasPathSatisfying("$.memberId", notNull())
                .hasPathSatisfying("$.email", equalsTo(request));

        MemberRegisterResponse response =
                objectMapper.readValue(result.getResponse().getContentAsString(), MemberRegisterResponse.class);

        Member member = memberRepository.findById(response.memberId()).orElseThrow();

        assertThat(member.getEmail().address()).isEqualTo(request.email());
        assertThat(member.getNickname()).isEqualTo(request.nickname());

    }

    @Test
    void duplicateEmail() throws JsonProcessingException {
        memberRegister.register(MemberFixture.createMemberRegisterRequest());

        MemberRegisterRequest request = MemberFixture.createMemberRegisterRequest();
        String requestJson = objectMapper.writeValueAsString(request);

        MvcTestResult result = mvcTester.post()
                .uri("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .exchange();

        assertThat(result)
                .apply(print())
                .hasStatus(HttpStatus.CONFLICT);
    }
}
