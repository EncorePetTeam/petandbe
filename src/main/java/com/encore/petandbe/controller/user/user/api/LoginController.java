package com.encore.petandbe.controller.user.user.api;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.service.oauth.KakaoOauthService;
import com.encore.petandbe.service.user.user.UserService;

@RestController
@RequestMapping("/oauth")
public class LoginController {
	private final KakaoOauthService kakaoOauthService;
	private final UserService userService;

	public LoginController(KakaoOauthService kakaoOauthService, UserService userService) {
		this.kakaoOauthService = kakaoOauthService;
		this.userService = userService;
	}

	//code = authorization code
	@GetMapping("/kakao")
	public ResponseEntity<String> loginByKakao(@RequestParam String code) throws IOException, InterruptedException {
		Map<String, String> user = kakaoOauthService.findUserByKakao(code);

		userService.loginByKakao(user);


		return ResponseEntity.ok().body(code);
	}

}
