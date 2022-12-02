package com.encore.petandbe.controller.user.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.controller.user.user.requests.UserUpdateRequest;
import com.encore.petandbe.controller.user.user.responses.UserIdResponse;
import com.encore.petandbe.service.user.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping({"/{user-id}"})
	public ResponseEntity<UserDetailsResponse> viewUserDetails(@PathVariable("user-id") Long userId) {
		return ResponseEntity.ok().body(userService.findUserById(userId));
	}

	@PutMapping({"/{user-id}"})
	public ResponseEntity<UserIdResponse> updateUser(
		@PathVariable("user-id") Long userId,
		@RequestBody UserUpdateRequest userUpdateRequest) {
		return ResponseEntity.ok()
			.body(new UserIdResponse(userService.updateUser(userUpdateRequest, userId)));
	}

}



