package jp.kopher.customloginpage.service

import jp.kopher.customloginpage.model.Course
import java.util.Optional

interface CourseService {
    fun createCourse(course: Course): Course
    fun findCourseById(courseId: Long): Optional<Course>
    fun findAllCourses(): Iterable<Course>
    fun updateCourse(course: Course): Course
    fun deleteCourseById(courseId: Long)
}
