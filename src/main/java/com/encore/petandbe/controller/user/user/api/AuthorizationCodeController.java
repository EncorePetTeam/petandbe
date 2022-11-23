package com.encore.petandbe.controller.user.user.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthorizationCodeController {

	@GetMapping ("/user/login/kakao")
	public ResponseEntity<String> authorizationCodeRequest() throws Exception{

		URL url = new URL("https://kauth.kakao.com/oauth/authorize?client_id=d2f9960e9c71353ff62ea0eafaaed0f3&redirect_uri=http://localhost:8080/oauth/kakao&response_type=code");
		URLConnection connection = url.openConnection();

		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream())))
		{
			String line;
			while ((line = in.readLine()) != null){
				System.out.println(line);
			}

		}

		return ResponseEntity.ok().body("");
	}


}
