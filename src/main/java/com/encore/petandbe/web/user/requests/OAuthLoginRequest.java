package com.encore.petandbe.web.user.requests;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthLoginRequest {
	private String authorizationCode;

	public OAuthLoginRequest(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
}

// Redirect로 오는 인가 코드 받는 DTO