package com.encore.petandbe.controller.user.user.responses;


import lombok.Getter;

@Getter
public class UserSignUpResponse {

    String token;
    String email;
    String nickname;

    public UserSignUpResponse(String token, String email, String nickname) {
        this.token = token;
        this.email = email;
        this.nickname = nickname;
    }
}
