package jp.co.topu.configrelationaldb

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.sql.DataSource

@SpringBootTest
class ConfigRelationalDbApplicationTests(
    @Autowired private val dataSource: DataSource,
) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() {
        assertThat(dataSource.javaClass.name).isEqualTo("com.zaxxer.hikari.HikariDataSource")
        assertThat(dataSource.connection.metaData.databaseProductName).isEqualTo("H2")
    }

}
