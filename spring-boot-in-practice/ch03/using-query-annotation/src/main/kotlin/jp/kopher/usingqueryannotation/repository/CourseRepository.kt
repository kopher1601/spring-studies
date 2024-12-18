package jp.kopher.usingqueryannotation.repository

import jakarta.transaction.Transactional
import jp.kopher.usingqueryannotation.domain.Course
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CrudRepository<Course, Long> {

    @Query("select c from Course c where c.category = ?1")
    fun findAllByCategory(category: String): Iterable<Course>

    @Query("select c from Course c where c.category = :category and c.rating > :rating")
    fun findAllByCategoryAndRatingGreaterThan(
        @Param("category") category: String,
        @Param("rating") rating: Int
    ): Iterable<Course>

    @Query(value = "select * from COURSE where rating = ?1", nativeQuery = true)
    fun findAllByRating(rating: Int): Iterable<Course>

    @Modifying
    @Transactional
    @Query("update Course c set c.rating = :rating where c.name = :name")
    fun updateCourseRatingByName(@Param("rating") rating: Int, @Param("name") name: String): Int

}
