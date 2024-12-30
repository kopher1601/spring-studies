package jp.co.kopher.coursetracker.listener

import jp.co.kopher.coursetracker.event.UserRegistrationEvent
import jp.co.kopher.coursetracker.model.ApplicationUser
import jp.co.kopher.coursetracker.service.EmailVerificationService
import org.springframework.context.ApplicationListener
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.util.*


@Service
class EmailVerificationListener(
    val mailSender: JavaMailSender,
    val verificationService: EmailVerificationService,
): ApplicationListener<UserRegistrationEvent> {
    override fun onApplicationEvent(event: UserRegistrationEvent) {
        val user = event.getUser()
        var verificationId = ""
        if (user.username != null) {
            verificationId = verificationService.generateVerification(user.username!!)
        }

        val message = SimpleMailMessage()
        message.subject = "Course Tracker Account Verification"
        message.text = getText(user, verificationId)
        message.setTo(user.email)
        mailSender.send(message)
    }

    private fun getText(user: ApplicationUser, verificationId: String): String {
        val encodedVerificationId = String(Base64.getEncoder().encode(verificationId.toByteArray()))
        val buffer = StringBuffer()
        buffer.append("Dear ").append(user.firstName).append(" ").append(user.lastName).append(",")
            .append(System.lineSeparator()).append(
                System.lineSeparator()
            )
        buffer.append("Your account has been successfully created in the Course Tracker application. ")

        buffer.append("Activate your account by clicking the following link: http://localhost:8080/verify/email?id=")
            .append(encodedVerificationId)
        buffer.append(System.lineSeparator()).append(System.lineSeparator())
        buffer.append("Regards,").append(System.lineSeparator()).append("Course Tracker Team")
        return buffer.toString()
    }
}