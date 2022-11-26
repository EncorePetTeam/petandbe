package com.encore.petandbe.model.user.dto;

import com.encore.petandbe.repository.UserRepository;

public class LoginDto {

	private final String userCode;
	private final UserRepository userRepository;

	public LoginDto(String userCode, UserRepository userRepository) {
		this.userCode = userCode;
		this.userRepository = userRepository;
	}

}
