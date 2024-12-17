package jp.kopher.customquerymethods

import jp.kopher.customquerymethods.domain.Course
import jp.kopher.customquerymethods.repository.CourseRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class CustomQueryMethodsApplicationTests(
    @Autowired private val courseRepository: CourseRepository,
) {

    @Test
    fun givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
        // 과정 목록 저장
        courseRepository.saveAll(getCourseList())
        assertThat(courseRepository.findAllByCategory("Spring")).hasSize(3)
        assertThat(courseRepository.existsByName("JavaScript for All")).isTrue()
        assertThat(courseRepository.existsByName("Mastering JavaScript")).isFalse()
        assertThat(courseRepository.countByCategory("Python")).isEqualTo(2)
        assertThat(courseRepository.findByNameStartsWith("Getting Started")).hasSize(3)
    }

    private fun getCourseList(): List<Course> {
        val rapidSpringBootCourse = Course(
            name = "Rapid Spring Boot Application Development",
            category = "Spring",
            rating = 4,
            description = "Spring Boot gives all the power of the Spring Framework without all of the complexity"
        )
        val springSecurityDslCourse =
            Course(
                name = "Getting Started with Spring Security DSL",
                category = "Spring",
                rating = 5,
                description = "Learn Spring Security DSL in easy steps"
            )
        val springCloudKubernetesCourse = Course(
            name = "Getting Started with Spring Cloud Kubernetes",
            category = "Spring",
            rating = 3,
            description = "Master Spring Boot application deployment with Kubernetes"
        )
        val rapidPythonCourse =
            Course(
                name = "Getting Started with Python",
                category = "Python",
                rating = 5,
                description = "Learn Python concepts in easy steps"
            )
        val gameDevelopmentWithPython =
            Course(
                name = "Game Development with Python",
                category = "Python",
                rating = 3,
                description = "Learn Python by developing 10 wonderful games"
            )
        val javaScriptForAll =
            Course(
                name = "JavaScript for All",
                category = "JavaScript",
                rating = 4,
                description = "Learn basic JavaScript syntax that can apply to anywhere"
            )
        val javaScriptCompleteGuide = Course(
            name = "JavaScript Complete Guide",
            category = "JavaScript",
            rating = 5,
            description = "Master JavaScript with Core Concepts and Web Development"
        )

        return listOf(
            rapidSpringBootCourse,
            springSecurityDslCourse,
            springCloudKubernetesCourse,
            rapidPythonCourse,
            gameDevelopmentWithPython,
            javaScriptForAll,
            javaScriptCompleteGuide
        )
    }
}
