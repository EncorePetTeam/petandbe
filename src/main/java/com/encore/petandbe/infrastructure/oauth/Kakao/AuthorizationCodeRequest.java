package com.encore.petandbe.infrastructure.oauth.Kakao;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 인가 코드를 받기 위해 보내는 request
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Component
public class AuthorizationCodeRequest {

	private String clientId;
	private String redirectUri;
	private String responseType;

	public AuthorizationCodeRequest(String clientId, String redirectUri, String responseType) {
		this.clientId = clientId;
		this.redirectUri = redirectUri;
		this.responseType = responseType;
	}
}
