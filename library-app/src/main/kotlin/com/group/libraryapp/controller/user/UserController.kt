package com.group.libraryapp.controller.user

import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.service.user.UserService
import org.springframework.web.bind.annotation.*

@RequestMapping("/user")
@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun saveUser(@RequestBody request: UserCreateRequest) {
        this.userService.saveUser(request)
    }

    @GetMapping
    fun getUsers(): List<UserResponse> {
        return this.userService.getUsers()
    }

    @PutMapping
    fun updateUserName(@RequestBody request: UserUpdateRequest) {
        this.userService.updateUserName(request)
    }

    @DeleteMapping
    fun deleteUser(@RequestParam name: String) {
        this.userService.deleteUser(name)
    }
}