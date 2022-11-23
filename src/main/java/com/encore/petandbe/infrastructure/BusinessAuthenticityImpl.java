package com.encore.petandbe.infrastructure;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.encore.petandbe.controller.user.host.requests.HostRegistrationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BusinessAuthenticityImpl implements BusinessAuthenticity {

	private ObjectMapper objectMapper;

	public BusinessAuthenticityImpl(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean checkAuthenticity(HostRegistrationRequest hostRegistrationRequest) {

		String serviceKey = "SDknnRn8ZJSaepNdwIW7WeV5ZUqyNaK31vkjERfM7Lwi%2F9xJ8ArunrvWB0PYzHQJz5rawN8U4eADXTDXOzZoSg%3D%3D";

		try {
			String url = "http://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=" + serviceKey;

			Map<String, String> params = new LinkedHashMap<>();
			params.put("b_no", hostRegistrationRequest.getRegistrationNumber());
			params.put("start_dt", hostRegistrationRequest.getOpenDate());
			params.put("p_nm", hostRegistrationRequest.getHostName());

			List<Object> list = new ArrayList<>();
			list.add(params);

			Map<String, Object> body = new LinkedHashMap<>();
			body.put("businesses", list);

			String data = objectMapper.writeValueAsString(body);

			HttpRequest request = HttpRequest.newBuilder(URI.create(url))
				.POST(HttpRequest.BodyPublishers.ofString(data))
				.header("Content-Type", "application/json")
				.header("Data-Type", "JSON")
				.build();

			HttpClient client = HttpClient.newHttpClient();
			var response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

			String result = objectMapper.readTree(response.body()).get("data").get(0).get("valid").asText();

			if (result.equals("01")) {
				return true;
			}

		} catch (MalformedURLException | JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		return false;
	}
}
