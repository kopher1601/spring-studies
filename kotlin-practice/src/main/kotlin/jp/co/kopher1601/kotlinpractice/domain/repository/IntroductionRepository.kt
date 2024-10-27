package jp.co.kopher1601.kotlinpractice.domain.repository

import jp.co.kopher1601.kotlinpractice.domain.entity.Introduction
import org.springframework.data.jpa.repository.JpaRepository

interface IntroductionRepository: JpaRepository<Introduction, Long>