package jp.kopher1601.splearn.domain;

import java.util.regex.Pattern;

/**
 * JPA 3.2.0 부터 @Embeddable 을 record 에서 사용할 수 있다
 */
public record Email(String address) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    // 생성자처럼 작동함
    public Email {
        if (!EMAIL_PATTERN.matcher(address).matches()) {
            throw new IllegalArgumentException("Invalid email address: " + address);
        }
    }
}
