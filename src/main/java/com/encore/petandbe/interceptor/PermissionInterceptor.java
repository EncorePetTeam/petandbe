package com.encore.petandbe.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.encore.petandbe.config.Permission;
import com.encore.petandbe.exception.AuthenticationException;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.jwt.JwtTokenService;
import com.encore.petandbe.utils.CookieUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

	private final JwtTokenService jwtTokenService;

	public PermissionInterceptor(JwtTokenService jwtTokenService) {
		this.jwtTokenService = jwtTokenService;
	}

	@Value("${jwt.access-token.name}")
	private String accessTokenName;

	@Value("${jwt.refresh-token.name}")
	private String refreshTokenName;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		System.out.println("----- 호출 -----");
		if (checkHandlerMethod(handler) && !checkPermission(handler)) {
			return true;
		}

		if (!isJwtNull(request)) {
			throw new JwtException("jwt token is null");
		}

		String accessToken = parseAccessToken(request);
		String refreshToken = parseRefreshToken(request);

		String validatedAccessToken = jwtTokenService.validateJwtToken(accessToken, refreshToken, response);

		Claims jwtContents = jwtTokenService.getJwtContents(validatedAccessToken);

		if (checkJwtRoleForHandlerRole(jwtContents, handler)) {
			request.setAttribute(Role.USER.getValue(), jwtTokenService.parseUserIdByClaims(jwtContents));
			return true;
		}

		throw new AuthenticationException("Invalid Token");
	}

	private boolean checkHandlerMethod(Object handler) {
		return handler instanceof HandlerMethod;
	}

	private boolean checkPermission(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
		return permission != null;
	}

	private boolean isJwtNull(HttpServletRequest request) {
		try {
			Cookie cookie = CookieUtil.getCookie(request, accessTokenName);
			String token = cookie.getValue();
			return token != null;
		} catch (NullPointerException e) {
			throw new AuthenticationException("Could not found access token");
		}
	}

	private String parseAccessToken(HttpServletRequest request) {
		return CookieUtil.getCookie(request, accessTokenName).getValue();
	}

	private String parseRefreshToken(HttpServletRequest request) {
		try {
			return CookieUtil.getCookie(request, refreshTokenName).getValue();
		} catch (NullPointerException e) {
			throw new NullPointerException("Refresh token is not found");
		}
	}

	private boolean checkJwtRoleForHandlerRole(Claims claims, Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
		return claims.get("authorization").equals(permission.role().toString());
	}
}
