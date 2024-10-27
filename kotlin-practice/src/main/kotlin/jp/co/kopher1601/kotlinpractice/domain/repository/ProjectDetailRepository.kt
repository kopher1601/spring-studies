package jp.co.kopher1601.kotlinpractice.domain.repository

import jp.co.kopher1601.kotlinpractice.domain.entity.ProjectDetail
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectDetailRepository: JpaRepository<ProjectDetail, Long>