package com.encore.petandbe.controller.user.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.service.user.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{token}")
	public ResponseEntity<UserDetailsResponse> userDetails(@PathVariable("token") String token) {
		return ResponseEntity.ok().body(userService.findUserDetails(token));
	}

}
