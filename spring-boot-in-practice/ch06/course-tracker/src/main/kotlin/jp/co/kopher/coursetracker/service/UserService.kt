package jp.co.kopher.coursetracker.service

import jp.co.kopher.coursetracker.dto.UserDto
import jp.co.kopher.coursetracker.model.ApplicationUser

interface UserService {
    fun createUser(userDto: UserDto): ApplicationUser
    fun save(applicationUser: ApplicationUser?): ApplicationUser
    fun findByUsername(username: String?): ApplicationUser?
}