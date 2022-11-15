package com.encore.petandbe.controller.user.user.api;

import com.encore.petandbe.controller.user.user.requests.UserSignInRequest;
import com.encore.petandbe.controller.user.user.requests.UserSignUpRequest;
import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.controller.user.user.responses.UserSignInResponse;
import com.encore.petandbe.controller.user.user.responses.UserSignUpResponse;
import com.encore.petandbe.service.user.user.UserService;
import com.encore.petandbe.service.user.user.UserSignInService;
import com.encore.petandbe.service.user.user.UserSignUpService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final UserSignUpService userSignUpService;
	private final UserSignInService userSignInService;

	public UserController(UserService userService, UserSignUpService userSignUpService,
		UserSignInService userSignInService) {
		this.userService = userService;
		this.userSignUpService = userSignUpService;
		this.userSignInService = userSignInService;
	}

	@GetMapping("/{token}")
	public ResponseEntity<UserDetailsResponse> userDetails(@PathVariable("token") String token) {
		return ResponseEntity.ok().body(userService.findUserDetails(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<UserSignUpResponse> userSignUp(@RequestBody UserSignUpRequest userSignUpRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userSignUpService.userSignUp(userSignUpRequest));
	}

	@GetMapping("/signin")
	public ResponseEntity<UserSignInResponse> userSignIn(@RequestBody UserSignInRequest userSignInRequest) {
		return ResponseEntity.ok().body(userSignInService.userSignIn(userSignInRequest));
	}

}
