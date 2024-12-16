package jp.kopher.customspringdatarepository

import jp.kopher.customspringdatarepository.domain.Course
import jp.kopher.customspringdatarepository.repository.CustomizedCourseRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CustomSpringDataRepositoryApplicationTests(
    @Autowired private val customizedCourseRepository: CustomizedCourseRepository
) {

    @Test
    fun givenCreateCourseWhenFindAllCoursesThenExpectOneCourse() {
        val course = Course(
            name = "Rapid spring boot",
            category = "Spring",
            rating = 4,
            description = "Spring boot is awesome",
        )
        customizedCourseRepository.save(course)

        assertThat(customizedCourseRepository.findAll()).hasSize(1)
    }

}
