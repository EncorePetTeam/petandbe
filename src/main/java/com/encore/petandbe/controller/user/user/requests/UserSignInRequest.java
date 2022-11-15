package com.encore.petandbe.controller.user.user.requests;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSignInRequest {

    private final String token;
    private final String email;
    private final String nickname;

    public UserSignInRequest(String token, String email, String nickname) {
        this.token = token;
        this.email = email;
        this.nickname = nickname;
    }
}
