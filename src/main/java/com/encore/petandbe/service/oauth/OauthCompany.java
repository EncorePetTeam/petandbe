package com.encore.petandbe.service.oauth;

import com.encore.petandbe.exception.WrongOauthTypeException;

public enum OauthCompany {
	KAKAO("kakao"), NAVER("naver");

	private final String stringName;

	OauthCompany(String stringName) {
		this.stringName = stringName;
	}

	public String getStringName() {
		return stringName;
	}

	public static OauthCompany findEnumByString(String company) {
		switch (company){
			case "kakao":
				return KAKAO;
			case "naver":
				return NAVER;
			default:
				throw new WrongOauthTypeException("Wrong Oauth Name");
		}


	}
}
