package jp.co.kopher1601.kopherlog.repository

import jp.co.kopher1601.kopherlog.domain.Post
import jp.co.kopher1601.kopherlog.request.PostSearch

interface PostRepositoryCustom {
    fun getList(postSearch: PostSearch): List<Post>
}