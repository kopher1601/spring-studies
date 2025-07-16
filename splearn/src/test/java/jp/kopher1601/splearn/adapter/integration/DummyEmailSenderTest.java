package jp.kopher1601.splearn.adapter.integration;

import jp.kopher1601.splearn.domain.shared.Email;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import static org.assertj.core.api.Assertions.assertThat;

class DummyEmailSenderTest {

    @Test
    @StdIo
    void dummyEmailSender(StdOut out) {
        DummyEmailSender emailSender = new DummyEmailSender();

        emailSender.send(new Email("kopher@splearn.app"), "subject", "body");

        assertThat(out.capturedLines()[0]).isEqualTo("DummyEmailSender.sendEmail[address=kopher@splearn.app]");
    }
}