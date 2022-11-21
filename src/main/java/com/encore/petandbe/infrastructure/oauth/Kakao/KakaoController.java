package com.encore.petandbe.infrastructure.oauth.Kakao;

import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.web.user.requests.OAuthLoginRequest;
import com.encore.petandbe.web.user.results.OAuthLoginResult;

@RestController
public class KakaoController {
	// 컨트롤러에서 해야하는 건 Kakao Auth server에서 Redirect로 보내주는 인가 코드를 받고
	// PostMapping으로 인가 코드를 보내서 토큰을 받아온다.

	KakaoOauth kakaoOauth = new KakaoOauth();
	private final OAuthLoginRequest oAuthLoginRequest;
	private final OAuthLoginResult oAuthLoginResult;

	public KakaoController(OAuthLoginRequest oAuthLoginRequest, OAuthLoginResult oAuthLoginResult) {
		this.oAuthLoginRequest = oAuthLoginRequest;
		this.oAuthLoginResult = oAuthLoginResult;
	}
	//
	public
}
