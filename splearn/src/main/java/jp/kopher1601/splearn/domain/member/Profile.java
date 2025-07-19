package jp.kopher1601.splearn.domain.member;

import java.util.regex.Pattern;

public record Profile(String address) {
    private static final Pattern PROFILE_ADDRESS_PATTERN = Pattern.compile("[a-z0-9]+");

    // 생성자처럼 작동함
    public Profile {
        if (address == null || (!address.isEmpty() && !PROFILE_ADDRESS_PATTERN.matcher(address).matches())) {
            throw new IllegalArgumentException("Invalid profile address: " + address);
        }

        if (address.length() > 15) {
            throw new IllegalArgumentException("Profile address is too long: " + address);
        }
    }

    public String url() {
        return "@" + address;
    }
}
