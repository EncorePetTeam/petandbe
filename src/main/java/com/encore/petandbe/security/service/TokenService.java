package com.encore.petandbe.security.service;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;


@Service
public class TokenService{
	private String secretKey = "token-secret-key";

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	@Value("${spring.security.expiration-time.token-period}")
	long tokenPeriod;

	@Value("${spring.security.expiration-time.refresh-period}")
	long refreshPeriod;

	public Token generateToken(String uid, String role) {

		Claims claims = Jwts.claims().setSubject(uid);
		claims.put("role", role);

		return new Token("accesstoken", "refreshToken");
	}


	public boolean verifyToken(String token) {
		try {
			Jws<Claims> claims = Jwts
				.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token);
			return claims.getBody()
				.getExpiration()
				.after(new Date());
		} catch (Exception e) {
			return false;
		}
	}


	public String getUid(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}
}
