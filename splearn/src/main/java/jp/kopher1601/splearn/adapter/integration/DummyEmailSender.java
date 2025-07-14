package jp.kopher1601.splearn.adapter.integration;

import jp.kopher1601.splearn.application.required.EmailSender;
import jp.kopher1601.splearn.domain.Email;
import org.springframework.stereotype.Component;

@Component
public class DummyEmailSender implements EmailSender {
    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("DummyEmailSender.send" + email);
    }
}
