package jp.kopher.usingquerydsl.repository

import jp.kopher.usingquerydsl.domain.Course
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CrudRepository<Course, Long>, QuerydslPredicateExecutor<Course> {}
