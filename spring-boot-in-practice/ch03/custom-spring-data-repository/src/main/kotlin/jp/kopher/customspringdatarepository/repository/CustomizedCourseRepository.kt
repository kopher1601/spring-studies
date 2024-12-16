package jp.kopher.customspringdatarepository.repository

import jp.kopher.customspringdatarepository.domain.Course
import org.springframework.stereotype.Repository

@Repository
interface CustomizedCourseRepository : BaseRepository<Course, Long> {
}
