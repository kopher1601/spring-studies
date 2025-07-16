package jp.kopher1601.splearn;

import jp.kopher1601.splearn.application.member.required.EmailSender;
import jp.kopher1601.splearn.domain.member.MemberFixture;
import jp.kopher1601.splearn.domain.member.PasswordEncoder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SplearnTestConfiguration {
    @Bean
    public EmailSender emailSender() {
        return (email, subject, body) -> System.out.println("Sending email = " + email);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return MemberFixture.createPasswordEncoder();
    }
}
