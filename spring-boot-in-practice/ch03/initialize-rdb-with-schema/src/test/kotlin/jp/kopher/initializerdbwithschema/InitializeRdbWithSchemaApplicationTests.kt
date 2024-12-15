package jp.kopher.initializerdbwithschema

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.sql.DataSource


@SpringBootTest
class CourseTrackerSpringBootApplicationTests {

    @Autowired
    private lateinit var dataSource: DataSource

    @Test
    fun `when count all courses then expect five courses`() {
        var noOfCourses = 0

        dataSource.connection.use { connection ->
            connection.prepareStatement("SELECT COUNT(1) FROM COURSES").use { ps ->
                ps.executeQuery().use { rs ->
                    while (rs.next()) {
                        noOfCourses = rs.getInt(1)
                    }
                    assertThat(noOfCourses).isEqualTo(5)
                }
            }
        }
    }
}
