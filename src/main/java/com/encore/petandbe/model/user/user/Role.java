package com.encore.petandbe.model.user.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	USER("userId"),
	ADMIN("adminId"); // OAuth

	private final String value;
}