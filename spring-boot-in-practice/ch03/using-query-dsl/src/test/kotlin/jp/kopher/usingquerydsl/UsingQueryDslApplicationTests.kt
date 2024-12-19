package jp.kopher.usingquerydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jp.kopher.usingquerydsl.domain.Course
import jp.kopher.usingquerydsl.domain.QCourse
import jp.kopher.usingquerydsl.repository.CourseRepository
import org.assertj.core.api.Assertions.*
import org.assertj.core.util.Lists.newArrayList
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UsingQueryDslApplicationTests(
    @Autowired private val courseRepository: CourseRepository,
    @Autowired private val queryFactory: JPAQueryFactory
) {

    @Test
    fun givenCoursesCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {
        courseRepository.saveAll(getCourseList())

        val course = QCourse.course
        val query1 = queryFactory.from(course).where(course.category.eq("Spring"))
        assertThat(query1.fetch().size).isEqualTo(3)

        val query2 = queryFactory.from(course).where(course.category.eq("Spring").and(course.rating.gt(3)))
        assertThat(query2.fetch().size).isEqualTo(2)

        val descOrderSpecifier = course.rating.desc()
        assertThat(newArrayList(courseRepository.findAll(descOrderSpecifier))[0].name).isEqualTo("Getting Started with Spring Security DSL");

    }

    private fun getCourseList(): List<Course> {
        val rapidSpringBootCourse = Course(
            _name = "Rapid Spring Boot Application Development",
            category = "Spring",
            rating = 4,
            description = "Spring Boot gives all the power of the Spring Framework without all of the complexity"
        )
        val springSecurityDslCourse =
            Course(
                _name = "Getting Started with Spring Security DSL",
                category = "Spring",
                rating = 5,
                description = "Learn Spring Security DSL in easy steps"
            )
        val springCloudKubernetesCourse = Course(
            _name = "Getting Started with Spring Cloud Kubernetes",
            category = "Spring",
            rating = 3,
            description = "Master Spring Boot application deployment with Kubernetes"
        )
        val rapidPythonCourse =
            Course(
                _name = "Getting Started with Python",
                category = "Python",
                rating = 5,
                description = "Learn Python concepts in easy steps"
            )
        val gameDevelopmentWithPython =
            Course(
                _name = "Game Development with Python",
                category = "Python",
                rating = 3,
                description = "Learn Python by developing 10 wonderful games"
            )
        val javaScriptForAll =
            Course(
                _name = "JavaScript for All",
                category = "JavaScript",
                rating = 4,
                description = "Learn basic JavaScript syntax that can apply to anywhere"
            )
        val javaScriptCompleteGuide = Course(
            _name = "JavaScript Complete Guide",
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
