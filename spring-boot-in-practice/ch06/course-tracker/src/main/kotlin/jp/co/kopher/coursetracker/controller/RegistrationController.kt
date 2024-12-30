package jp.co.kopher.coursetracker.controller

import jakarta.validation.Valid
import jp.co.kopher.coursetracker.dto.UserDto
import jp.co.kopher.coursetracker.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegistrationController(
    val userService: UserService,
) {

    @GetMapping("/addUser")
    fun register(model: Model): String {
        model.addAttribute("user", UserDto())
        return "add-user"
    }

    @PostMapping("/adduser")
    fun register(
        @Valid @ModelAttribute("user") userDto: UserDto,
        bindingResult: BindingResult,
    ): String {
        if (bindingResult.hasErrors()) {
            return "add-user"
        }

        userService.createUser(userDto)
        return "redirect: adduser ? success"
    }
}