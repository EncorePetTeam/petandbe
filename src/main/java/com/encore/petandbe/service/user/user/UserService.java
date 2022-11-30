package com.encore.petandbe.service.user.user;

import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.controller.user.user.requests.UserUpdateRequest;
import com.encore.petandbe.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDetailsResponse findUserById(Long userId){
		return null;
	}

	public Long updateUser(UserUpdateRequest userUpdateRequest, Long userId){
		return null;
	}

}
