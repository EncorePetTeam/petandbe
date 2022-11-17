package com.encore.petandbe.controller.user.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {
	@GetMapping("/test")
	public String index() {
		return "Hello World";
	}
}