package jp.co.kopher1601.kopherlog.request

import jakarta.validation.constraints.NotBlank
import jp.co.kopher1601.kopherlog.exception.InvalidRequest

data class PostCreate(
    @field:NotBlank(message = "타이틀을 입력하세요")
    val title: String,

    @field:NotBlank(message = "내용을 입력하세요")
    val content: String,
) {
    fun validate() {
        if (this.title.contains("바보")) {
            throw InvalidRequest("title")
        }
    }
}
