package com.encore.petandbe.controller.user.user.requests;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSignUpRequest {

    String token;
    String email;
    String nickname;

    public UserSignUpRequest(String token, String email, String nickname) {
        this.token = token;
        this.email = email;
        this.nickname = nickname;
    }

}
