package jp.kopher.namedquerymethod.repository

import jp.kopher.namedquerymethod.domain.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CrudRepository<Course, Long> {

    fun findAllByCategoryAndRating(category: String, rating: Int): Iterable<Course>

}
