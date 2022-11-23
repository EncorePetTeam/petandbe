package com.encore.petandbe.controller.user.user.api;

import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.service.user.user.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
