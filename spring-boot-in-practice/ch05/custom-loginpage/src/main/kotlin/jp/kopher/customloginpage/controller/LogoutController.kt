package jp.kopher.customloginpage.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping

@Controller
class LogoutController {

    @PostMapping("/doLogout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication != null) {
            SecurityContextLogoutHandler().logout(request, response, authentication)
        }

        return "redirect:/login"
    }
}
