package com.encore.petandbe.service.oauth;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.encore.petandbe.infrastructure.oauth.Kakao.KakaoOauth;

@Service
public class KakaoOauthService {

	private final KakaoOauth kakaoOauth;

	public KakaoOauthService(KakaoOauth kakaoOauth) {
		this.kakaoOauth = kakaoOauth;
	}

	public Map<String,String> findUserByKakao(String code) throws IOException, InterruptedException {
		return kakaoOauth.convertAuthorizationCodeToInfo(code);
	}
}
