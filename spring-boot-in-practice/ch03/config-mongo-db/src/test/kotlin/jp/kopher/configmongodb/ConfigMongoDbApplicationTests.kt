package jp.kopher.configmongodb

import com.mongodb.BasicDBObjectBuilder
import com.mongodb.DBObject
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataMongoTest
@ExtendWith(SpringExtension::class)
class ConfigMongoDbApplicationTests(
    @Autowired val mongoTemplate: MongoTemplate
) {

    @Test
    fun givenObjectAvailableWhenSaveToCollectionThenExpectValue() {
        // given
        val mongoObject = BasicDBObjectBuilder.start().add("Kopher", "Spring Boot In Practice").get()

        // when
        mongoTemplate.save(mongoObject, "collection")

        // then
        assertThat(mongoTemplate.findAll(DBObject::class.java, "collection"))
            .extracting("Kopher")
            .containsOnly("Spring Boot In Practice")
    }
}
