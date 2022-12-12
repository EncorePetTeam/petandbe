package com.encore.petandbe.controller.oauth.responses;

import lombok.Getter;

@Getter
public class LogoutResponse {
	Long userId;

	public LogoutResponse(Long userId) {
		this.userId = userId;
	}
}
