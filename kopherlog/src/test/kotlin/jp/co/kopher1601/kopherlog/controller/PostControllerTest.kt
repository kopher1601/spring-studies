package jp.co.kopher1601.kopherlog.controller

import com.fasterxml.jackson.databind.ObjectMapper
import jp.co.kopher1601.kopherlog.repository.PostRepository
import jp.co.kopher1601.kopherlog.request.PostCreate
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest @Autowired constructor(
    private val mvc: MockMvc,
    private val objectMapper: ObjectMapper,
    private val postRepository: PostRepository,
) {

    @Test
    @DisplayName("/posts 요청시 데이터베이스에 값이 저장된다.")
    fun test3() {

        // given
        val jsonString = objectMapper.writeValueAsString(PostCreate("테스트입니다.", "테스트 내용입니다."))

        // when
        mvc.perform(
            MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
        )
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())

        // then
        assertThat(postRepository.findAll())
            .hasSize(1)
            .extracting("title", "content")
            .containsExactlyInAnyOrder(tuple("테스트입니다.", "테스트 내용입니다."))

    }
}