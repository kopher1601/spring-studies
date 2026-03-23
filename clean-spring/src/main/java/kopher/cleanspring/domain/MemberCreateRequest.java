package kopher.cleanspring.domain;

// private final field 와 생성자, getter 를 작성해준다.
public record MemberCreateRequest(String email, String nickname, String password) {
}
