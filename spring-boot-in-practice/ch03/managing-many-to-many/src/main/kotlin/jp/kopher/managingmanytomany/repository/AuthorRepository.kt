package jp.kopher.managingmanytomany.repository

import jp.kopher.managingmanytomany.domain.Author
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository: CrudRepository<Author, Long> {

    @Query("SELECT new jp.kopher.managingmanytomany.dto.AuthorCourseDto(c.id, a.name, c.name, c.description) " +
            "from AUTHOR a, COURSES c, AUTHORS_COURSES ac where a.id = ac.authorId and c.id = ac.courseId and ac.authorId =?1")
    fun getAuthorCourseInfo(authorId: Long)
}
