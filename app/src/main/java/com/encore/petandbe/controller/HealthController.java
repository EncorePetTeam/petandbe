package com.encore.petandbe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	@GetMapping("/healthz")
	public ResponseEntity<String> healthTest() {
		return ResponseEntity.ok().body("Success");
	}
}
