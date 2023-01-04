package com.encore.petandbe.exception;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.encore.petandbe.controller.accommodation.review.requests.DeleteReviewRequests;
import com.encore.petandbe.interceptor.PermissionInterceptor;
import com.encore.petandbe.model.user.user.Role;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Transactional
class GlobalExceptionHandlerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PermissionInterceptor permissionInterceptor;

	@Test
	@DisplayName("NonExistResourceException - Exception handel success")
	void nonExistResourceExceptionSuccess() throws Exception {
		//given
		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("pageNum", "1");
		param.add("amount", "10");

		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.get("/review-list/user")
			.params(param)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), 125426847));
		//then
		resultActions.andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	@DisplayName("WrongRequestException - Exception handel success")
	void wrongRequestExceptionSuccess() throws Exception {
		//given
		Long reviewId = 1L;
		Long userId = 1L;

		DeleteReviewRequests deleteReviewRequests = new DeleteReviewRequests(reviewId, userId);

		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.delete("/review/{review-id}", reviewId)
			.content(objectMapper.writeValueAsString(deleteReviewRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
}