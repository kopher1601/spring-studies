package jp.co.kopher.coursetracker.service.impl

import jp.co.kopher.coursetracker.dto.UserDto
import jp.co.kopher.coursetracker.model.ApplicationUser
import jp.co.kopher.coursetracker.repository.UserRepository
import jp.co.kopher.coursetracker.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class DefaultUserService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
) : UserService {
    override fun createUser(userDto: UserDto): ApplicationUser {
        val applicationUser = ApplicationUser(
            firstName = userDto.firstName,
            lastName = userDto.lastName,
            email = userDto.email,
            username = userDto.username,
            password = passwordEncoder.encode(userDto.password)
        )
        return userRepository.save(applicationUser)
    }

    override fun save(applicationUser: ApplicationUser?): ApplicationUser {
        return userRepository.save(applicationUser!!)
    }

    override fun findByUsername(username: String?): ApplicationUser? {
        return userRepository.findByUsername(username)

    }
}