package com.encore.petandbe.infrastructure.oauth;

import lombok.Getter;

@Getter
public class OauthUserInfoDTO {

	private final String userCode;
	private final String nickname;

	public OauthUserInfoDTO(String userId, String nickname) {
		this.userCode = userId;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "OauthUserInfoDTO{" +
			"userId='" + userCode + '\'' +
			", nickname='" + nickname + '\'' +
			'}';
	}
}
