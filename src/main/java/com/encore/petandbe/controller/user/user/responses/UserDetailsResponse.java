package com.encore.petandbe.controller.user.user.responses;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
