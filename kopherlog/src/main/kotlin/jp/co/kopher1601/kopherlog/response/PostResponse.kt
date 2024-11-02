package jp.co.kopher1601.kopherlog.response

data class PostResponse(
    val id: Long,
    private var _title: String,
    val content: String,
) {

    val title: String
        get() = _title

    init {
        _title = title.substring(0, title.length.coerceAtMost(10))
    }
}