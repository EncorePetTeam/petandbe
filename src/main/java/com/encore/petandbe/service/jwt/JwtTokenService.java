package com.encore.petandbe.service.jwt;

import java.security.Key;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.encore.petandbe.infrastructure.oauth.RedisManager;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.utils.CookieUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtTokenService {

	private final RedisManager redisManager;

	public JwtTokenService(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-token.expire-length}")
	private Integer accessTokenExpireTime;

	@Value("${jwt.refresh-token.expire-length}")
	private Integer refreshTokenExpireTime;

	@Value("${jwt.issuer}")
	private String issuer;

	@Value("${jwt.access-token.name}")
	private String accessTokenName;

	@Value("${jwt.refresh-token.name}")
	private String refreshTokenName;

	@Value("${jwt.logout-keyword}")
	private String logoutKeyword;

	private static final String AUTHORIZATION_KEY = "authorization";
	private static final String USER_ID_KEY = "user_id";
	private static final Integer ACCESS_TOKEN_NUMBER = 0;
	private static final Integer REFRESH_TOKEN_NUMBER = 1;

	public String createAccessToken(Long userId, Role role) {
		Date validity = java.sql.Timestamp.valueOf(LocalDateTime.now().plusSeconds(accessTokenExpireTime));

		Key secretKey = createSecretKey(secret);

		return Jwts.builder()
			.setSubject("Access Token")
			.setIssuer(issuer)
			.claim(AUTHORIZATION_KEY, role)
			.claim(USER_ID_KEY, userId)
			.signWith(SignatureAlgorithm.HS512, secretKey)
			.setExpiration(validity)
			.compact();
	}

	public String createRefreshToken(Long userId) {
		Date validity = java.sql.Timestamp.valueOf(LocalDateTime.now().plusSeconds(refreshTokenExpireTime));

		Key secretKey = createSecretKey(secret);

		return Jwts.builder()
			.setSubject("Refresh Token")
			.setIssuer(issuer)
			.claim(USER_ID_KEY, userId)
			.signWith(SignatureAlgorithm.HS512, secretKey)
			.setExpiration(validity)
			.compact();
	}

	public Duration getRefreshTokenDuration() {
		return Duration.ofSeconds(refreshTokenExpireTime);
	}

	public Duration getAccessTokenDuration() {
		return Duration.ofSeconds(accessTokenExpireTime);
	}

	public Integer getRefreshTokenExpireTime() {
		return refreshTokenExpireTime;
	}

	public String validateJwtToken(String accessToken, String refreshToken, HttpServletResponse response) {
		try {
			Claims claims = Jwts.parser()
				.setSigningKey(createSecretKey(secret))
				.parseClaimsJws(accessToken).getBody();
			checkLogout(claims);
			return accessToken;
		} catch (ExpiredJwtException e) {
			log.warn("Jwt time was expired.", e);
			return reissueToken(accessToken, refreshToken, response);
		}
	}

	private void checkLogout(Claims claims) {
		String userId = claims.get(USER_ID_KEY).toString();
		if (redisManager.getValues(userId).equals(logoutKeyword)) {
			throw new JwtException("Token has already been logged out.");
		}
	}

	public String reissueToken(String accessToken, String refreshToken, HttpServletResponse response) {
		String userId = String.valueOf(Jwts.parser()
			.setSigningKey(createSecretKey(secret))
			.parseClaimsJws(refreshToken)
			.getBody()
			.get(USER_ID_KEY));
		String token = redisManager.getValues(userId);
		String[] tokens = token.split(" ");

		if (tokens[ACCESS_TOKEN_NUMBER].equals(accessToken) && tokens[REFRESH_TOKEN_NUMBER].equals(refreshToken)) {
			return updateToken(userId, response);
		}

		throw new JwtException("Tokens do not match.");
	}

	private String updateToken(String userId, HttpServletResponse response) {
		String createdAccessToken = createAccessToken(Long.parseLong(userId), Role.USER);
		String createdRefreshToken = createRefreshToken(Long.parseLong(userId));
		redisManager.setValuesWithDuration(userId, createdAccessToken + " " + createdRefreshToken,
			getRefreshTokenDuration());
		response.addCookie(
			CookieUtil.createCookie(accessTokenName, createdAccessToken, this.getRefreshTokenExpireTime()));
		response.addCookie(
			CookieUtil.createCookie(refreshTokenName, createdRefreshToken, this.getRefreshTokenExpireTime()));
		return createdAccessToken;
	}

	public Claims getJwtContents(String token) {
		return Jwts.parser().setSigningKey(createSecretKey(secret)).parseClaimsJws(token).getBody();
	}

	private Key createSecretKey(String secret) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);

		return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
	}

	public Integer parseUserIdByClaims(Claims claims) {
		return (Integer)claims.get(USER_ID_KEY);
	}
}
