package jp.co.kopher1601.kopherlog.repository

import jp.co.kopher1601.kopherlog.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>