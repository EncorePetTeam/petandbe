package com.encore.petandbe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.encore.petandbe.security.OAuth2SuccessHandler;
import com.encore.petandbe.security.jwt.JwtAuthFilter;
import com.encore.petandbe.security.service.CustomOAuth2UserService;
import com.encore.petandbe.security.service.TokenService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final CustomOAuth2UserService oAuth2UserService;
	private final OAuth2SuccessHandler successHandler;
	private final TokenService tokenService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers("/token/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new JwtAuthFilter(tokenService),
				UsernamePasswordAuthenticationFilter.class)
			.oauth2Login().loginPage("/token/expired")
			.successHandler(successHandler)
			.userInfoEndpoint().userService(oAuth2UserService);

		http.addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
	}
}