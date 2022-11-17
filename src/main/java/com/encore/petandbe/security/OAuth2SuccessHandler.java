package com.encore.petandbe.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.encore.petandbe.security.dto.Token;
import com.encore.petandbe.security.dto.UserDto;
import com.encore.petandbe.security.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final TokenService tokenService;
	private final UserRequestMapper userRequestMapper;
	private final ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
		throws IOException, ServletException {
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
		UserDto userDto = userRequestMapper.toDto(oAuth2User);

		log.info("Principal에서 꺼낸 User = {}", oAuth2User);
		// 최초 로그인이라면 회원가입 처리를 한다.
		String targetUrl;
		log.info("토큰 발행 시작");

		Token token = (Token)tokenService.generateToken(userDto.getEmail(), "USER");
		log.info("{}", token);
		targetUrl = UriComponentsBuilder.fromUriString("/home")
			.queryParam("token", "token")
			.build().toUriString();
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
}
