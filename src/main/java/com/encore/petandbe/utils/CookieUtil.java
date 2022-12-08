package com.encore.petandbe.utils;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

public class CookieUtil {

	private static final String COOKIE_PATH = "/";

	@Value("${jwt.access-token.name}")
	private String accessTokenName;

	@Value("${jwt.refresh-token.name}")
	private String refreshTokenName;

	private CookieUtil() {
	}

	public static Cookie createCookie(String cookieName, String value, Integer expireTime) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(expireTime);
		cookie.setPath(COOKIE_PATH);
		return cookie;
	}

	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(cookieName)).findFirst().orElse(null);
	}

	public static Cookie removeCookie(String cookieName) {
		Cookie expiredCookie = new Cookie(cookieName, null);
		expiredCookie.setMaxAge(0);
		expiredCookie.setPath(COOKIE_PATH);
		return expiredCookie;
	}
}
