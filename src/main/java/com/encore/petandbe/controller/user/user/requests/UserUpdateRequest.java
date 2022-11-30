package com.encore.petandbe.controller.user.user.requests;

import lombok.Getter;

@Getter
public class UserUpdateRequest {

	private final String nickname;
	private final String email;

	public UserUpdateRequest(String nickname, String email) {
		this.nickname = nickname;
		this.email = email;
	}
}
