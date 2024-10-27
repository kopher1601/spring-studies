package jp.co.kopher1601.kotlinpractice.domain.repository

import jp.co.kopher1601.kotlinpractice.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository: JpaRepository<Project, Long>