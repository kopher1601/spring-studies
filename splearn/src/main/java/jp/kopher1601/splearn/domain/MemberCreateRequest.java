package jp.kopher1601.splearn.domain;

public record MemberCreateRequest(String email, String nickname, String password) {
}
