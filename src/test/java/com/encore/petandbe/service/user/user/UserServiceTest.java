package com.encore.petandbe.service.user.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.service.user.user.dto.UserUpdateDTO;

@SpringBootTest
@Transactional
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	private final Long userId = 1L;
	private final String nickname = "뚱이";
	private final String email = "nowayhome@google.com";

	@Test
	@DisplayName("Find User Service - Success")
	void findUserById() {
		//given//when
		UserDetailsResponse user = userService.findUserById(1L);
		//then
		assertThat(user).isNotNull();

	}

	@Test
	@DisplayName("Update User Service - Success")
	void updateUser() {
		//given
		User user = userRepository.findById(userId).get();
		//when
		user.updateUser(UserUpdateDTO.builder()
			.nickname(nickname)
			.email(email)
			.build()
		);
		//then
		assertThat(user.getNickname()).isEqualTo(nickname);
		assertThat(user.getEmail()).isEqualTo(email);
	}
}