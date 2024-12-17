package jp.kopher.customquerymethods.repository

import jp.kopher.customquerymethods.domain.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.stream.Stream

@Repository
interface CourseRepository : CrudRepository<Course, Long> {

    fun findAllByCategory(category: String): Iterable<Course>

    // 이름 기준으로 정렬
    fun findAllByCategoryOrderByName(category: String): Iterable<Course>
    fun existsByName(name: String): Boolean
    fun countByCategory(category: String): Long
    fun findByNameOrCategory(name: String, category: String): Iterable<Course>
    fun findByNameStartsWith(name: String): Iterable<Course>
    fun streamAllByCategory(category: String): Stream<Course>

}
