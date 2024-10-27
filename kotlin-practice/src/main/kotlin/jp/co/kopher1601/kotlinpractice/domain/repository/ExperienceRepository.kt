package jp.co.kopher1601.kotlinpractice.domain.repository

import jp.co.kopher1601.kotlinpractice.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository: JpaRepository<Experience, Long>