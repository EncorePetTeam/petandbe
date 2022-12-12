package com.encore.petandbe.infrastructure.oauth;

import static com.encore.petandbe.service.oauth.OauthCompany.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.encore.petandbe.service.oauth.OauthCompany;
import com.encore.petandbe.service.oauth.OauthInformation;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@Component
public class OauthManager {

	private final OauthInformation oauthInformation;

	public OauthManager(OauthInformation oauthInformation) {
		this.oauthInformation = oauthInformation;
	}

	public OauthUserInfoDTO convertAuthorizationCodeToInfo(String authorizationCode, OauthCompany oauthCompany) throws
		IOException,
		InterruptedException {
		return convertAccessTokenToOAuthInformation(
			convertAuthorizationCodeToAccessToken(authorizationCode, oauthCompany), oauthCompany);
	}

	private String convertAuthorizationCodeToAccessToken(String authorizationCode, OauthCompany oauthCompany) throws
		IOException,
		InterruptedException {
		var urlEncodedBody = new String(new UrlEncodedFormEntity(
			List.of(
				new BasicNameValuePair("client_id", oauthInformation.getClientId(oauthCompany)),
				new BasicNameValuePair("client_secret", oauthInformation.getClientSecret(oauthCompany)),
				new BasicNameValuePair("code", authorizationCode),
				new BasicNameValuePair("grant_type", oauthInformation.getGrantType(oauthCompany)),
				new BasicNameValuePair("redirect_url", oauthInformation.getRedirectUri(oauthCompany))
			)
		).getContent().readAllBytes());

		HttpRequest request = HttpRequest
			.newBuilder(URI.create(oauthInformation.getTokenUri(KAKAO)))
			.POST(HttpRequest.BodyPublishers.ofString(urlEncodedBody))
			.header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
			.build();

		HttpClient client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

		return new ObjectMapper().readTree(response.body()).get("access_token").asText();
	}

	private OauthUserInfoDTO convertAccessTokenToOAuthInformation(String accessToken, OauthCompany oauthCompany) throws
		IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(oauthInformation.getUserInfoUri(oauthCompany)))
			.GET()
			.setHeader("Authorization", "Bearer " + accessToken)
			.setHeader("Content-Type", "application/json;charset=utf-8")
			.build();

		var result = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		var tree = new ObjectMapper().readTree(result.body());
		var oauthUserProfile = tree.get("kakao_account").get("profile");

		return new OauthUserInfoDTO(tree.get("id").asText(), oauthUserProfile.get("nickname").asText());
	}
}
