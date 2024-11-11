package jp.co.kopher1601.kopherlog.exception

class PostNotFound(
    override val message: String = "존재하지 않는 글 입니다."
): RuntimeException()