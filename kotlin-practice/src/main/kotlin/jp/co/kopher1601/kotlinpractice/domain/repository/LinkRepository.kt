package jp.co.kopher1601.kotlinpractice.domain.repository

import jp.co.kopher1601.kotlinpractice.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository: JpaRepository<Link, Long>