package com.encore.petandbe.utils.mapper;

import com.encore.petandbe.controller.user.user.requests.UserUpdateRequest;
import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.service.user.user.dto.UserUpdateDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

	public static UserDetailsResponse convertUserToUserDetailsResponse(User user){
		return UserDetailsResponse.builder()
			.nickname(user.getNickname())
			.email(user.getEmail())
			.build();
	}

	public static UserUpdateDTO convertUpdateRequestToUpdateDTO(UserUpdateRequest request){
		return UserUpdateDTO.builder()
			.nickname(request.getNickname())
			.email(request.getEmail())
			.build();
	}
}
