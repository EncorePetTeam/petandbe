package com.encore.petandbe.controller.user.user.responses;

import lombok.Getter;

@Getter
public class UserIdResponse {

	Long userId;

	public UserIdResponse(Long userId) {
		this.userId = userId;
	}
}
