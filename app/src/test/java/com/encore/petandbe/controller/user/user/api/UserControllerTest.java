package com.encore.petandbe.controller.user.user.api;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.encore.petandbe.controller.user.user.requests.UserUpdateRequest;
import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.interceptor.PermissionInterceptor;
import com.encore.petandbe.service.user.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureRestDocs
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private PermissionInterceptor permissionInterceptor;

	@Autowired
	private ObjectMapper objectMapper;

	private final Long userId = 1L;
	private final String nickname = "스폰지밥";
	private final String email = "good@naver.com";

	@Test
	@DisplayName("View User Details Controller - Success")
	void viewUserDetails() throws Exception {
		//given
		UserDetailsResponse response = new UserDetailsResponse(userId, nickname, email);
		when(userService.findUserById(userId)).thenReturn(response);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(get("/user/{user-id}", userId)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("view-user-details",
				pathParameters(parameterWithName("user-id").description("상세 정보를 조회한 유저 id")),
				responseFields(
					fieldWithPath("userId").type(JsonFieldType.NUMBER).description("상세 정보를 조회한 유저 id"),
					fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일")
				))
			);
	}

	@Test
	@DisplayName("Update User Controller - Success")
	void updateUser() throws Exception {
		//given
		UserUpdateRequest request = new UserUpdateRequest(nickname, email);
		when(userService.updateUser(request, userId)).thenReturn(userId);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(put("/user/{user-id}", userId)
			.content(objectMapper.writeValueAsString(request))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("user-update",
				pathParameters(
					parameterWithName("user-id").description("수정할 유저 id")
				),
				requestFields(
					fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일")
				),
				responseFields(
					fieldWithPath("userId").type(JsonFieldType.NUMBER).description("수정된 유저 id")
				)
			));

	}
}