package jp.kopher.managedomainobjectswithjpa.repository

import jp.kopher.managedomainobjectswithjpa.domain.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: CrudRepository<Course, Long> {
}
