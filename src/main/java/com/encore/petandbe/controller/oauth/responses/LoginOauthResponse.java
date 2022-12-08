package com.encore.petandbe.controller.oauth.responses;

import lombok.Getter;

@Getter
public class LoginOauthResponse {
	private Long userId;

	public LoginOauthResponse(Long userId) {
		this.userId = userId;
	}
}
