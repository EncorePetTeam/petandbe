package com.encore.petandbe.controller.user.user.responses;

public class UserDetailsResponse {

    String token;
    String email;
    String nickname;

    public UserDetailsResponse(String token, String email, String nickname) {
        this.token = token;
        this.email = email;
        this.nickname = nickname;
    }
}
