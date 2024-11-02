package jp.co.kopher1601.kopherlog.service

import jp.co.kopher1601.kopherlog.domain.Post
import jp.co.kopher1601.kopherlog.repository.PostRepository
import jp.co.kopher1601.kopherlog.request.PostCreate
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
) {

    fun write(postCreate: PostCreate) {
        val post = Post(postCreate.title, postCreate.content)
        postRepository.save(post)
    }

    fun get(id: Long): Post {
        return (postRepository.findByIdOrNull(id)
            ?: throw NotFoundException())

    }
}