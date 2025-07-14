package jp.kopher1601.splearn.adapter.integration;

import jp.kopher1601.splearn.application.required.EmailSender;
import jp.kopher1601.splearn.domain.Email;
import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Component;

@Component
@Fallback // 다른 빈을 찾다가 빈이 없을 경우 이걸 사용해라
public class DummyEmailSender implements EmailSender {

    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("DummyEmailSender.send" + email);
    }
}
