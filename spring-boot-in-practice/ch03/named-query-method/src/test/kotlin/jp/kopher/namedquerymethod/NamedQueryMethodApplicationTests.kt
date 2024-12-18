package jp.kopher.namedquerymethod

import jp.kopher.namedquerymethod.domain.Course
import jp.kopher.namedquerymethod.repository.CourseRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class NamedQueryMethodApplicationTests(
    @Autowired private val courseRepository: CourseRepository,
) {

    @Test
    fun givenCoursesCreatedWhenLoadCoursesBySpringCategoryThenExpectThreeCourses() {
        courseRepository.saveAll(getCourseList())
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
