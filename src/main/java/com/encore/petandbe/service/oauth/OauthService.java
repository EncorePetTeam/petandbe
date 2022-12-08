package com.encore.petandbe.service.oauth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.oauth.responses.LoginOauthResponse;
import com.encore.petandbe.controller.oauth.responses.LogoutResponse;
import com.encore.petandbe.infrastructure.oauth.OauthManager;
import com.encore.petandbe.infrastructure.oauth.OauthUserInfoDTO;
import com.encore.petandbe.infrastructure.oauth.RedisManager;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.service.jwt.JwtTokenService;
import com.encore.petandbe.utils.CookieUtil;

@Service
public class OauthService {

	private final OauthInformation oauthInformation;
	private final OauthManager oauthManager;
	private final JwtTokenService jwtTokenService;
	private final UserRepository userRepository;
	private final RedisManager redisManager;

	@Value("${jwt.access-token.name}")
	private String accessTokenName;

	@Value("${jwt.refresh-token.name}")
	private String refreshTokenName;

	@Value("${jwt.logout-keyword}")
	private String logoutKeyword;

	public OauthService(OauthInformation oauthInformation,
		OauthManager oauthManager, JwtTokenService jwtTokenProvider,
		UserRepository userRepository, RedisManager redisManager) {
		this.oauthInformation = oauthInformation;
		this.oauthManager = oauthManager;
		this.jwtTokenService = jwtTokenProvider;
		this.userRepository = userRepository;
		this.redisManager = redisManager;
	}

	@Transactional
	public String findOauthPageByCompanyName(OauthCompany companyName) {
		return getOauthUrlByCompanyName(companyName);
	}

	private String getOauthUrlByCompanyName(OauthCompany companyName) {
		return oauthInformation.getAuthorizationCodeUri(companyName);
	}

	@Transactional
	public LoginOauthResponse loginByOauth(String authorizationCode, OauthCompany companyName,
		HttpServletResponse httpServletResponse) throws
		IOException,
		InterruptedException {
		OauthUserInfoDTO oauthUserInfoDTO = oauthManager.convertAuthorizationCodeToInfo(authorizationCode, companyName);
		Optional<User> foundUser = userRepository.findByUserCode(oauthUserInfoDTO.getUserCode());
		User loginUser = checkUser(oauthUserInfoDTO, foundUser);
		String accessToken = jwtTokenService.createAccessToken(loginUser.getId(), loginUser.getRole());
		String refreshToken = jwtTokenService.createRefreshToken(loginUser.getId());
		String token = createToken(accessToken, refreshToken);
		redisManager.setValuesWithDuration(String.valueOf(loginUser.getId()), token,
			jwtTokenService.getRefreshTokenDuration());
		httpServletResponse.addCookie(
			CookieUtil.createCookie(accessTokenName, accessToken, jwtTokenService.getRefreshTokenExpireTime()));
		httpServletResponse.addCookie(
			CookieUtil.createCookie(refreshTokenName, refreshToken, jwtTokenService.getRefreshTokenExpireTime()));
		return new LoginOauthResponse(loginUser.getId());
	}

	private User checkUser(OauthUserInfoDTO oauthUserInfoDTO, Optional<User> foundUser) {
		return foundUser.isEmpty() ? userRepository.save(singUp(oauthUserInfoDTO)) : foundUser.get();
	}

	private User singUp(OauthUserInfoDTO oauthUserInfoDTO) {
		return User.builder()
			.userCode(oauthUserInfoDTO.getUserCode())
			.nickname(oauthUserInfoDTO.getNickname())
			.role(Role.USER)
			.build();
	}

	private String createToken(String accessToken, String refreshToken) {
		return accessToken + " " + refreshToken;
	}

	public LogoutResponse logoutByUserId(Long id, HttpServletResponse httpServletResponse) {
		httpServletResponse.addCookie(CookieUtil.removeCookie(accessTokenName));
		httpServletResponse.addCookie(CookieUtil.removeCookie(refreshTokenName));
		redisManager.setValuesWithDuration(String.valueOf(id), logoutKeyword, jwtTokenService.getAccessTokenDuration());
		return new LogoutResponse(id);
	}
}
