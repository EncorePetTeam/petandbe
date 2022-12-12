package com.encore.petandbe.service.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.encore.petandbe.exception.WrongOauthTypeException;

@Component
public class OauthInformation {

	@Value("${spring.security.oauth2.provider.kakao.authorization-uri}")
	private String kakaoAuthorizationUri;

	@Value("${spring.security.oauth2.provider.naver.authorization-uri}")
	private String naverAuthorizationUri;

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String kakaoClientId;

	@Value("${spring.security.oauth2.client.registration.naver.client-id}")
	private String naverClientId;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String kakaoRedirectUri;

	@Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
	private String naverRedirectUri;

	@Value("${spring.security.oauth2.provider.kakao.token-uri}")
	private String kakaoTokenUri;

	@Value("${spring.security.oauth2.provider.naver.token-uri}")
	private String naverTokenUri;

	@Value("${spring.security.oauth2.provider.kakao.user-info-uri}")
	private String kakaoUserInfoUri;

	@Value("${spring.security.oauth2.provider.naver.user-info-uri}")
	private String naverUserInfoUri;

	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String kakaoClientSecret;

	@Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
	private String kakaoGrantType;

	@Value("${spring.security.oauth2.client.registration.naver.client-secret}")
	private String naverClientSecret;

	@Value("${spring.security.oauth2.client.registration.naver.authorization-grant-type}")
	private String naverGrantType;


	OauthInformation() {
	}

	public String getAuthorizationCodeUri(OauthCompany companyName) {
		switch (companyName) {
			case KAKAO:
				return kakaoAuthorizationUri + "?client_id=" + kakaoClientId + "&redirect_uri=" + kakaoRedirectUri
					+ "&response_type=code";
			case NAVER:
				return naverAuthorizationUri + "?client_id=" + naverClientId + "&redirect_uri=" + naverRedirectUri
					+ "&response_type=code";
			default:
				throw new WrongOauthTypeException("Wrong Oauth Case");
		}
	}

	public String getTokenUri(OauthCompany companyName) {
		switch (companyName) {
			case KAKAO:
				return kakaoTokenUri;
			case NAVER:
				return naverTokenUri;
			default:
				throw new WrongOauthTypeException("Wrong Oauth Case");
		}
	}

	public String getUserInfoUri(OauthCompany companyName) {
		switch (companyName) {
			case KAKAO:
				return kakaoUserInfoUri;
			case NAVER:
				return naverUserInfoUri;
			default:
				throw new WrongOauthTypeException("Wrong Oauth Case");
		}
	}

	public String getClientId(OauthCompany companyName) {
		switch (companyName) {
			case KAKAO:
				return kakaoClientId;
			case NAVER:
				return naverClientId;
			default:
				throw new WrongOauthTypeException("Wrong Oauth Case");
		}
	}

	public String getClientSecret(OauthCompany companyName) {
		switch (companyName) {
			case KAKAO:
				return kakaoClientSecret;
			case NAVER:
				return naverClientSecret;
			default:
				throw new WrongOauthTypeException("Wrong Oauth Case");
		}
	}

	public String getGrantType(OauthCompany companyName) {
		switch (companyName) {
			case KAKAO:
				return kakaoGrantType;
			case NAVER:
				return naverGrantType;
			default:
				throw new WrongOauthTypeException("Wrong Oauth Case");
		}
	}

	public String getRedirectUri(OauthCompany companyName) {
		switch (companyName) {
			case KAKAO:
				return kakaoRedirectUri;
			case NAVER:
				return naverRedirectUri;
			default:
				throw new WrongOauthTypeException("Wrong Oauth Case");
		}
	}
}
