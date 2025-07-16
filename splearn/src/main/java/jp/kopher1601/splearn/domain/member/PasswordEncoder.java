package jp.kopher1601.splearn.domain.member;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String password, String passwordHash);
}
