package jp.co.kopher.coursetracker.controller

import jp.co.kopher.coursetracker.service.EmailVerificationService
import jp.co.kopher.coursetracker.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.Base64

@Controller
class EmailVerificationController(
    val verificationService: EmailVerificationService,
    val userService: UserService,
) {

    @GetMapping("/verify/email")
    fun verifyEmail(@RequestParam id: String): String {
        val actualId = Base64.getDecoder().decode(id.toByteArray())
        val username = verificationService.getUsernameForVerificationId(actualId.toString())
        if (username != null) {
            val user = userService.findByUsername(username)
            user?.verified = true
            userService.save(user)
            return "redirect:/login-verified"
        }
        return "redirect:/login-error"
    }

}