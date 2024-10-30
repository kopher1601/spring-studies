package jp.co.kopher1601.kopherlog.controller

import jp.co.kopher1601.kopherlog.request.PostCreate
import jp.co.kopher1601.kopherlog.service.PostService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService,
) {
    @PostMapping("/posts")
    fun post(@RequestBody @Validated postCreate: PostCreate) {
        postService.write(postCreate)
    }
}