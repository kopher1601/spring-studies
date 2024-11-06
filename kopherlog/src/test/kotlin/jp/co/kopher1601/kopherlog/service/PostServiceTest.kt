package jp.co.kopher1601.kopherlog.service

import jp.co.kopher1601.kopherlog.domain.Post
import jp.co.kopher1601.kopherlog.repository.PostRepository
import jp.co.kopher1601.kopherlog.request.PostCreate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class PostServiceTest @Autowired constructor(
    private val postService: PostService,
    private val postRepository: PostRepository,
) {
    @Test
    @DisplayName("글 작성")
    fun test1() {
        // given
        val postCreate = PostCreate("제목입니다.", "내용입니다.")

        // when
        postService.write(postCreate)

        // then
        val findPost = postRepository.findAll().firstOrNull()
        assertThat(findPost).isNotNull()
        assertThat(findPost?.title).isEqualTo("제목입니다.")
        assertThat(findPost?.content).isEqualTo("내용입니다.")
    }

    @Test
    @DisplayName("글 한 개 조회")
    fun test2() {
        // given
        val savedPost = postRepository.save(Post("foo", "bar"))

        // when
        val postResponse = postService.get(savedPost.id!!)

        // then
        assertThat(postResponse).isNotNull()
        assertThat(postResponse.title).isEqualTo("foo")
        assertThat(postResponse.content).isEqualTo("bar")
    }

    @Test
    @DisplayName("글 여러 개 조회")
    fun test3() {
        // given
        postRepository
            .saveAll(listOf(Post("foo1", "bar1"), Post("foo2", "bar2")))

        // when
        val postResponses = postService.getList()

        // then
        assertThat(postResponses).hasSize(2)

    }
}