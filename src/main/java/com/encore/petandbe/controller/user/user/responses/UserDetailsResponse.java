package com.encore.petandbe.controller.user.user.responses;

import lombok.Getter;

@Getter
public class UserDetailsResponse {

	private final Long userId;
	private final String nickname;
	private final String email;

	public UserDetailsResponse(Long userId, String nickname, String email) {
		this.userId = userId;
		this.nickname = nickname;
		this.email = email;
	}
}
