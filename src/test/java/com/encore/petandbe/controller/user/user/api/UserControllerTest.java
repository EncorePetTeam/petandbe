package com.encore.petandbe.controller.user.user.api;

import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.controller.user.user.responses.UserSignUpResponse;
import com.encore.petandbe.service.user.user.UserService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureRestDocs
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;


	@Test
	@DisplayName("find user information test - normal")
	void userDetailsSuccess() throws Exception {
		//given
		String token = "token123";
		String email = "gsnrl@naver.com";
		String nickname = "rnfjddl";

		UserDetailsResponse userDetailsResponse = new UserDetailsResponse(token, email, nickname);

		when(userService.findUserDetails(anyString())).thenReturn(userDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.get("/user/{token}", token)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("user-details",
				pathParameters(
					parameterWithName("token").description("OAuth의 token id")
				),
				responseFields(
					fieldWithPath("token").type(JsonFieldType.STRING).description("OAuth의 token id"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("유저이메일"),
					fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저닉네임")
				)
			)).andDo(print());

	}

}