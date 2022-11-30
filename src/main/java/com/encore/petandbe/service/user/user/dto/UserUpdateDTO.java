package com.encore.petandbe.service.user.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateDTO {

	private String nickname;
	private String email;

	public UserUpdateDTO(String nickname, String email) {
		this.nickname = nickname;
		this.email = email;
	}
}
