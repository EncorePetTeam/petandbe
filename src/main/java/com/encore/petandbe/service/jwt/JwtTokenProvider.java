package com.encore.petandbe.service.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.encore.petandbe.model.user.user.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-token.expire-length}")
	private Integer accessTokenExpireTime;

	@Value("${jwt.refresh-token.expire-length}")
	private Integer refreshTokenExpireTime;

	@Value("${jwt.issuer}")
	private String issuer;

	private static final String AUTHORIZATION_KEY = "authorization";
	private static final String USER_ID_KEY = "user_id";

	public String createAccessToken(User user) {
		long now = (new Date()).getTime();
		Date validity = new Date(now + this.accessTokenExpireTime);

		Key secretKey = createSecretKey(secret);

		return Jwts.builder()
			.setSubject("Access Token")
			.setIssuer(issuer)
			.claim(AUTHORIZATION_KEY, user.getRole())
			.claim(USER_ID_KEY, user.getId())
			.signWith(SignatureAlgorithm.HS512, secretKey)
			.setExpiration(validity)
			.compact();
	}

	public String createRefreshToken() {
		long now = (new Date()).getTime();
		Date validity = new Date(now + this.refreshTokenExpireTime);

		Key secretKey = createSecretKey(secret);

		return Jwts.builder()
			.setSubject("Refresh Token")
			.setIssuer(issuer)
			.signWith(SignatureAlgorithm.HS512, secretKey)
			.setExpiration(validity)
			.compact();
	}

	private Key createSecretKey(String secret) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);

		return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
	}
}
