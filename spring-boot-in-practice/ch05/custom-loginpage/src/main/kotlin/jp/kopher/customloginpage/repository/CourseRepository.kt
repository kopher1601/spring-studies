package jp.kopher.customloginpage.repository

import jp.kopher.customloginpage.model.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: CrudRepository<Course, Long> {
}
