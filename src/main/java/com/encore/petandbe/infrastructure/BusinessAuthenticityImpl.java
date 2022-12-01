package com.encore.petandbe.infrastructure;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.encore.petandbe.controller.user.host.requests.HostRegistrationRequest;
import com.encore.petandbe.exception.BusinessAuthenticityException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@PropertySource("classpath:application-secret.yml")
public class BusinessAuthenticityImpl implements BusinessAuthenticity {

	@Value("${spring.secret.business-authenticity.service-key}")
	private String serviceKey;
	@Value("${spring.secret.business-authenticity.url}")
	private String url;
	private final String requstKeyRegistrationNumber = "b_no";
	private final String requstKeyStartDate = "start_dt";
	private final String requstKeyCEOName = "p_nm";
	private final String successResult = "01";
	private final ObjectMapper objectMapper;

	public BusinessAuthenticityImpl(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean checkAuthenticity(HostRegistrationRequest hostRegistrationRequest) {

		String requestBodyJSON = getRequestBodyJSON(hostRegistrationRequest);

		HttpRequest httpRequest = getHttpRequest(requestBodyJSON);

		HttpResponse<String> response = getStringHttpResponse(httpRequest);

		String resultCode = getResultCode(response);

		return isAuthenticitySuccess(resultCode);
	}

	private String getResultCode(HttpResponse<String> response) {
		try {
			return objectMapper.readTree(response.body()).get("data").get(0).get("valid").asText();
		} catch (JsonProcessingException e) {
			throw new BusinessAuthenticityException("BusinessAuthenticity API response parsing exception");
		}
	}

	private boolean isAuthenticitySuccess(String resultCode) {
		return resultCode.equals(successResult);
	}

	private HttpResponse<String> getStringHttpResponse(HttpRequest httpRequest) {
		HttpClient client = HttpClient.newHttpClient();
		try {
			return client.send(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new BusinessAuthenticityException("Request BusinessAuthenticity API IOException");
		} catch (InterruptedException e) {
			throw new BusinessAuthenticityException("Request BusinessAuthenticity API Interrupted Exception");
		}
	}

	private HttpRequest getHttpRequest(String data) {
		return HttpRequest.newBuilder(URI.create(url + serviceKey))
			.POST(HttpRequest.BodyPublishers.ofString(data))
			.header("Content-Type", "application/json")
			.header("Data-Type", "JSON")
			.build();
	}

	private String getRequestBodyJSON(HostRegistrationRequest hostRegistrationRequest) {
		List<Object> requestParamList = getRequestParams(hostRegistrationRequest);

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("businesses", requestParamList);
		try {
			return objectMapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			throw new BusinessAuthenticityException("Request data parse to Json JsonProcessing Exception");
		}
	}

	private List<Object> getRequestParams(HostRegistrationRequest hostRegistrationRequest) {
		Map<String, String> requestParams = new LinkedHashMap<>();
		requestParams.put(requstKeyRegistrationNumber, hostRegistrationRequest.getRegistrationNumber());
		requestParams.put(requstKeyStartDate, hostRegistrationRequest.getOpenDate());
		requestParams.put(requstKeyCEOName, hostRegistrationRequest.getHostName());

		List<Object> requestParamList = new ArrayList<>();
		requestParamList.add(requestParams);
		return requestParamList;
	}
}