package jp.co.kopher1601.kopherlog.request

import kotlin.math.max
import kotlin.math.min

private const val MAX_SIZE = 100L

class PostSearch(
    private val page: Long = 1,
    val size: Long = 10,
) {

    val offset: Long
        get() = (max(1, this.page) - 1L) * min(size, MAX_SIZE)
}