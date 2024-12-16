package jp.kopher.managedomainobjectswithjpa

import jp.kopher.managedomainobjectswithjpa.domain.Course
import jp.kopher.managedomainobjectswithjpa.repository.CourseRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ManageDomainObjectsWithJpaApplicationTests(
    @Autowired private val courseRepository: CourseRepository,
) {


    @Test
    fun givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {

        val course = Course(
            name = "Rapid Spring boot application development",
            category = "Spring",
            _rating = 4,
            description = "Spring boot gives all the power of the Spring framework without all of complexities"
        )
        val savedCourse = courseRepository.save(course)

        assertThat(courseRepository.findById(savedCourse.id!!).get()).isEqualTo(course)
    }

    @Test
    fun givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse() {
        val course = Course(
            name = "Rapid Spring boot application development",
            category = "Spring",
            _rating = 4,
            description = "Spring boot gives all the power of the Spring framework without all of complexities"
        )
        courseRepository.save(course)
        course.rating = 5
        val savedCourse = courseRepository.save(course)

        assertThat(courseRepository.findById(savedCourse.id!!).get().rating).isEqualTo(5)
    }

    @Test
    fun givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
        val course = Course(
            name = "Rapid Spring boot application development",
            category = "Spring",
            _rating = 4,
            description = "Spring boot gives all the power of the Spring framework without all of complexities"
        )
        val savedCourse = courseRepository.save(course)

        assertThat(courseRepository.findById(savedCourse.id!!).get()).isEqualTo(course)

        courseRepository.delete(course)
        assertThat(courseRepository.findById(savedCourse.id!!).isPresent).isFalse()
    }
}
