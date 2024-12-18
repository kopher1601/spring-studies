package jp.kopher.usingpagingandsortingrepository.repository

import jp.kopher.usingpagingandsortingrepository.domain.Course
import org.springframework.data.repository.PagingAndSortingRepository

interface CourseRepository : PagingAndSortingRepository<Course, Long>
