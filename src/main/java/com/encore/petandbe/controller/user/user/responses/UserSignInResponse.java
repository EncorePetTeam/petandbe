package com.encore.petandbe.controller.user.user.responses;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSignInResponse {

    private final String token;
    private final String email;
    private final String nickname;

    public UserSignInResponse(String token, String email, String nickname) {
        this.token = token;
        this.email = email;
        this.nickname = nickname;
    }
}
