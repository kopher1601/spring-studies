package jp.kopher1601.splearn.application.required;

import jp.kopher1601.splearn.domain.Email;

/**
 * 이메일 발송
 */
public interface EmailSender {
    void send(Email email, String subject, String body);
}
