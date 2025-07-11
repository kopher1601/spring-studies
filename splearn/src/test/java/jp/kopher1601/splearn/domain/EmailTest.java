package jp.kopher1601.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmailTest {

    @Test
    void equality() {
        var email1 = new Email("kopher@splearn.app");
        var email2 = new Email("kopher@splearn.app");

        assertThat(email1).isEqualTo(email2);
    }

}