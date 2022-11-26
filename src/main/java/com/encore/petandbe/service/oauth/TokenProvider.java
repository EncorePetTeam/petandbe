package com.encore.petandbe.service.oauth;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.stereotype.Component;

import com.encore.petandbe.model.user.user.CustomUserDetails;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider implements InitializingBean {

	private static final String AUTHORITIES_KEY = "auth";
	private final String issuer;
	private final String secret;
	private final long tokenValidityInMilliseconds;
	private Key key;

	public TokenProvider(
		@Value("${jwt.issuer}") String issuer,
		@Value("${jwt.secret}") String secret,
		@Value("${jwt.access-token.expire-length}") long tokenValidityInSeconds) {
		this.issuer = issuer;
		this.secret = secret;
		this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
	}

	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public String createToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));

		long now = (new Date()).getTime();
		Date validity = new Date(now + this.tokenValidityInMilliseconds);

		return Jwts.builder()
			.setSubject(authentication.getName())
			.setIssuer(issuer)
			.claim(AUTHORITIES_KEY, authorities)
			.signWith(key, SignatureAlgorithm.HS512)
			.setExpiration(validity)
			.compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts
			.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();

		Collection<GrantedAuthority> authorities =
			Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		CustomUserDetails principal = new CustomUserDetails(claims.getId(), authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	public boolean validateToken(String token) {
		// try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		// } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
		// 	logger.info("잘못된 JWT 서명입니다.");
		// } catch (ExpiredJwtException e) {
		// 	logger.info("만료된 JWT 토큰입니다.");
		// } catch (UnsupportedJwtException e) {
		// 	logger.info("지원되지 않는 JWT 토큰입니다.");
		// } catch (IllegalArgumentException e) {
		// 	logger.info("JWT 토큰이 잘못되었습니다.");
		// }
		// return false;
	}
}
