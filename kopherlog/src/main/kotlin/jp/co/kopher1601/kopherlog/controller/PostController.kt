package jp.co.kopher1601.kopherlog.controller

import jp.co.kopher1601.kopherlog.domain.Post
import jp.co.kopher1601.kopherlog.request.PostCreate
import jp.co.kopher1601.kopherlog.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class PostController(
    private val postService: PostService,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts")
    fun post(@RequestBody @Validated postCreate: PostCreate) {
        postService.write(postCreate)
    }

    @GetMapping("/posts/{postId}")
    fun get(@PathVariable("postId") id: Long): Post {
         return postService.get(id)
    }
}