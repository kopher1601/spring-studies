package jp.kopher.usingpagingandsortingrepository

import jp.kopher.usingpagingandsortingrepository.domain.Course
import jp.kopher.usingpagingandsortingrepository.repository.CourseRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Condition
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort


@DataJpaTest
class UsingPagingAndSortingRepositoryApplicationTests(
    @Autowired val courseRepository: CourseRepository,
) {

    @Test
    fun givenDataAvailableWhenLoadFirstPageThenGetFiveRecords() {
        val pageable = PageRequest.of(0, 5)
        assertThat(courseRepository.findAll(pageable)).hasSize(5)
        assertThat(pageable.pageNumber).isEqualTo(0)

        val nextPageable = pageable.next()
        assertThat(courseRepository.findAll(nextPageable)).hasSize(4)
        assertThat(nextPageable.pageNumber).isEqualTo(1)
    }

    @Test
    fun givenDataAvailableWhenSortsFirstPageThenGetSortedData() {
        val pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")))

        val sortedFirstCourseCondition: Condition<Course> = object : Condition<Course>() {
            override fun matches(course: Course): Boolean {
                return course.id == 4L && course.name == "Cloud Native Spring Boot Application Development"
            }
        }
        assertThat(courseRepository.findAll(pageable)).first().has(sortedFirstCourseCondition)
    }

    @Test
    fun givenDataAvailableWhenApplyCustomSortThenGetSortedResult() {
        val customSortPageable = PageRequest.of(0, 5, Sort.by("rating").descending().and(Sort.by("name")))
        val customSortFirstCourseCondition: Condition<Course> = object : Condition<Course>() {
            override fun matches(course: Course): Boolean {
                return course.id == 2L && course.name == "Getting Started with Spring Security DSL"
            }
        }
        assertThat(courseRepository.findAll(customSortPageable)).first().has(customSortFirstCourseCondition)
    }
}
