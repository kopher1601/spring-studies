package jp.co.kopher1601.kopherlog.service

import jp.co.kopher1601.kopherlog.domain.Post
import jp.co.kopher1601.kopherlog.repository.PostRepository
import jp.co.kopher1601.kopherlog.request.PostCreate
import jp.co.kopher1601.kopherlog.request.PostEdit
import jp.co.kopher1601.kopherlog.request.PostSearch
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
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
        val requestPosts = (0 until 20).map { i ->
            Post(
                _title = "코퍼로그 $i",
                _content = "키치죠지맨선 $i"
            )
        }.toList()
        postRepository.saveAll(requestPosts)

        // when
        val postResponses = postService.getList(PostSearch(1, 10))

        // then
        assertThat(postResponses).hasSize(10)
        assertThat(postResponses[0].title).isEqualTo("코퍼로그 19")
        assertThat(postResponses[1].title).isEqualTo("코퍼로그 18")

    }

    @Test
    @DisplayName("글 제목 수정")
    fun test4() {
        // given
        val savedPost = postRepository.save(Post("foo", "bar"))
        val postEdit = PostEdit(
            title = "코퍼로그"
        )

        // when
        postService.edit(savedPost.id!!, postEdit)

        // then
        val foundPost = postRepository.findByIdOrNull(savedPost.id!!)
        assertThat(foundPost).isNotNull()
        assertThat(foundPost?.title).isEqualTo("코퍼로그")
        assertThat(foundPost?.content).isEqualTo("bar")
    }

    @Test
    @DisplayName("게시글 삭제")
    fun test5() {
        // given
        val savedPost = postRepository.save(Post("foo", "bar"))

        // when
        postService.delete(savedPost.id!!)

        // then
        val count = postRepository.count()
        assertThat(count).isEqualTo(0)
    }

}