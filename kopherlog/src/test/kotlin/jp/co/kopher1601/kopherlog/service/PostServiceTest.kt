package jp.co.kopher1601.kopherlog.service

import jp.co.kopher1601.kopherlog.domain.Post
import jp.co.kopher1601.kopherlog.repository.PostRepository
import jp.co.kopher1601.kopherlog.request.PostCreate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

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
        val post = postService.get(savedPost.id!!)

        // then
        assertThat(post).isNotNull()
        assertThat(post.title).isEqualTo("foo")
        assertThat(post.content).isEqualTo("bar")
    }
}