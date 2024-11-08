package jp.co.kopher1601.kopherlog.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import jp.co.kopher1601.kopherlog.domain.Post
import jp.co.kopher1601.kopherlog.domain.QPost
import jp.co.kopher1601.kopherlog.request.PostSearch


class PostRepositoryImpl(
    private val query: JPAQueryFactory,
): PostRepositoryCustom {

    override fun getList(postSearch: PostSearch): List<Post> {
        return query.selectFrom(QPost.post)
            .limit(postSearch.size)
            .offset(postSearch.offset)
            .orderBy(QPost.post.id.desc())
            .fetch()
    }

}