package jp.kopher.introducingspringsecurity.repository

import jp.kopher.introducingspringsecurity.model.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: CrudRepository<Course, Long> {
}
