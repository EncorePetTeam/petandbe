package com.encore.petandbe.model.user.dto;

public class TokenDto {

	private final String jwt;

	public TokenDto(String jwt) {
		this.jwt = jwt;
	}
}