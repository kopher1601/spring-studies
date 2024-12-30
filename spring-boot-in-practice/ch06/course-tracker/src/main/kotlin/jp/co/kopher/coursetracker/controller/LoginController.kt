package jp.co.kopher.coursetracker.controller

import jp.co.kopher.coursetracker.dto.UserDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/login-error")
    fun loginError(model: Model): String {
        model.addAttribute("loginError", true)
        return "login"
    }
}