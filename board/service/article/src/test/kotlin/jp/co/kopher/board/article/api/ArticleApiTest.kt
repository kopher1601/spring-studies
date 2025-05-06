package jp.co.kopher.board.article.api

import jp.co.kopher.board.article.service.response.ArticleResponse
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestClient


class ArticleApiTest {

    val restClient = RestClient.create("http://localhost:9000")

    @Test
    fun createTest() {
        val response = create(ArticleCreateRequest("hi", "my content", 1L, 1L))
        println("response = ${response}")
    }

    private fun create(request: ArticleCreateRequest): ArticleResponse? {
        return restClient.post()
            .uri("/v1/articles")
            .body(request)
            .retrieve()
            .body(ArticleResponse::class.java)
    }

    @Test
    fun readTest() {
        val response = read(178018306868310016L)
        println("response = ${response}")
    }

    private fun read(id: Long): ArticleResponse? {
        return restClient.get()
            .uri("/v1/articles/$id")
            .retrieve()
            .body(ArticleResponse::class.java)
    }

    @Test
    fun updateTest() {
        val response = update(178018306868310016L, ArticleUpdateRequest("hi2", "my content2"))
        val readResponse = read(178018306868310016L)
        println("response = ${readResponse}")
    }

    private fun update(id: Long, request: ArticleUpdateRequest): ArticleResponse? {
        return restClient.put()
            .uri("/v1/articles/$id")
            .body(request)
            .retrieve()
            .body(ArticleResponse::class.java)
    }

    @Test
    fun deleteTest() {
        restClient.delete()
            .uri("/v1/articles/{articleId}", 178018306868310016L)
            .retrieve()
            .toBodilessEntity()
    }

    data class ArticleCreateRequest(
        val title: String,
        val content: String,
        val boardId: Long,
        val writerId: Long,
    )

    data class ArticleUpdateRequest(
        val title: String,
        val content: String,
    )

}