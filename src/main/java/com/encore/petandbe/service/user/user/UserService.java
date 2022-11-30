package com.encore.petandbe.service.user.user;

import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.controller.user.user.requests.UserUpdateRequest;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.UserMapper;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDetailsResponse findUserById(Long userId){
		User user = userRepository.findById(userId)
			.orElseThrow(()-> new NonExistResourceException("User could not be found"));

		return UserMapper.convertUserToUserDetailsResponse(user);
	}

	public Long updateUser(UserUpdateRequest userUpdateRequest, Long userId){

		User user = userRepository.findById(userId)
			.orElseThrow(()-> new NonExistResourceException("User could not be found"));

		user.updateUser(
			UserMapper.convertUpdateRequestToUpdateDTO(userUpdateRequest));

		return userId;
	}

}
