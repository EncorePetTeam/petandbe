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

	private static final String SWAGGER_URI = "/docs/index.html";
	private static final String SWAGGER_CSS_URI = "/docs/swagger-ui.css";
	private static final String SWAGGER_BUNDLE_URI = "/docs/swagger-ui-bundle.js";
	private static final String SWAGGER_PRESET_URI = "/docs/swagger-ui-standalone-preset.js";
	private static final String SWAGGER_OPEN_API = "/docs/open-api-3.0.1.json";
	private static final String SWAGGER_FAVICON_16 = "/docs/favicon-16x16.png";
	private static final String SWAGGER_FAVICON_32 = "/docs/favicon-32x32.png";
	private static final String FAVICON_ICO = "/favicon.ico";

	private static final String ERROR = "/error";

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

		if (checkStaticUri(request)) {
			return true;
		}

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

	private boolean checkStaticUri(HttpServletRequest request) {
		return request.getRequestURI().equals(SWAGGER_URI) || request.getRequestURI().equals(SWAGGER_PRESET_URI)
			|| request.getRequestURI().equals(SWAGGER_BUNDLE_URI) || request.getRequestURI().equals(SWAGGER_CSS_URI)
			|| request.getRequestURI().equals(ERROR) || request.getRequestURI().equals(SWAGGER_FAVICON_16)
			|| request.getRequestURI().equals(SWAGGER_FAVICON_32) || request.getRequestURI().equals(SWAGGER_OPEN_API)
			|| request.getRequestURI().equals(FAVICON_ICO);
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

