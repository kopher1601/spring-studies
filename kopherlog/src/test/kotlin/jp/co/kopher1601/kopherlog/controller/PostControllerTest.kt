package jp.co.kopher1601.kopherlog.controller

import com.fasterxml.jackson.databind.ObjectMapper
import jp.co.kopher1601.kopherlog.domain.Post
import jp.co.kopher1601.kopherlog.repository.PostRepository
import jp.co.kopher1601.kopherlog.request.PostCreate
import jp.co.kopher1601.kopherlog.request.PostEdit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.hamcrest.Matchers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@Transactional
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
        val jsonString = objectMapper.writeValueAsString(PostCreate(title = "테스트입니다.", content = "테스트 내용입니다."))

        // when
        mvc.perform(
            post("/posts")
                .contentType(APPLICATION_JSON)
                .content(jsonString)
        )
            .andExpect(status().isCreated())
            .andDo(print())

        // then
        assertThat(postRepository.findAll())
            .hasSize(1)
            .extracting("title", "content")
            .containsExactlyInAnyOrder(tuple("테스트입니다.", "테스트 내용입니다."))
    }

    @Test
    @DisplayName("글 한 개 조회")
    fun test4() {
        // given
        val savedPost = postRepository.save(Post("12345678901234", "bar"))

        // expected
        mvc.perform(
            get("/posts/{postId}", savedPost.id)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("1234567890"))
            .andExpect(jsonPath("$.content").value("bar"))
            .andDo(print())
    }

    @Test
    @DisplayName("글 여러 개 조회")
    fun test5() {
        // given
        val requestPosts = (0 until 30).map { i ->
            Post(
                _title = "코퍼로그 $i",
                _content = "키치죠지맨선 $i"
            )
        }.toList()
        postRepository.saveAll(requestPosts)

        // expected
        mvc.perform(
            get("/posts?page=2&size=5")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", Matchers.`is`(5)))
            .andExpect(jsonPath("$[0].title").value("코퍼로그 24"))
            .andExpect(jsonPath("$[0].content").value("키치죠지맨선 24"))
            .andExpect(jsonPath("$[1].title").value("코퍼로그 23"))
            .andExpect(jsonPath("$[1].content").value("키치죠지맨선 23"))
            .andDo(print())
    }

    @Test
    @DisplayName("패이지를 0 으로 요청하면 첫 페이지를 가져온다")
    fun test6() {
        // given
        val requestPosts = (0 until 30).map { i ->
            Post(
                _title = "코퍼로그 $i",
                _content = "키치죠지맨선 $i"
            )
        }.toList()
        postRepository.saveAll(requestPosts)

        // expected
        mvc.perform(
            get("/posts?page=0&size=5")
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", Matchers.`is`(5)))
            .andExpect(jsonPath("$[0].title").value("코퍼로그 29"))
            .andExpect(jsonPath("$[0].content").value("키치죠지맨선 29"))
            .andExpect(jsonPath("$[1].title").value("코퍼로그 28"))
            .andExpect(jsonPath("$[1].content").value("키치죠지맨선 28"))
            .andDo(print())
    }

    @Test
    @DisplayName("글 제목 수정")
    fun test7() {
        // given
        val savedPost = postRepository.save(Post("키치죠지맨션", "사고싶다"))

        val postEdit = PostEdit(
            title = "무사시사카이라도 좋아요",
            content = "사고싶다"
        )
        val jsonString = objectMapper.writeValueAsString(postEdit)

        // when
        mvc.perform(
            patch("/posts/{savedPost.id}", savedPost.id)
                .contentType(APPLICATION_JSON)
                .content(jsonString)
        )
            .andExpect(status().isOk())
            .andDo(print())
    }

    @Test
    @DisplayName("글 제목 삭제")
    fun test8() {
        // given
        val savedPost = postRepository.save(Post("키치죠지맨션", "사고싶다"))

        // when
        mvc.perform(
            delete("/posts/{savedPost.id}", savedPost.id)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andDo(print())
    }
}