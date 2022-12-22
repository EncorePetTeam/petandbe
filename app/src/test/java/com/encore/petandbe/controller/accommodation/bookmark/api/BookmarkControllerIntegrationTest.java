package com.encore.petandbe.controller.accommodation.bookmark.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.bookmark.requests.BookmarkRegistrationRequests;
import com.encore.petandbe.controller.accommodation.bookmark.requests.DeleteBookmarkRequests;
import com.encore.petandbe.interceptor.PermissionInterceptor;
import com.encore.petandbe.model.user.user.Role;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Transactional
class BookmarkControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PermissionInterceptor permissionInterceptor;

	@Autowired
	private ObjectMapper objectMapper;

	Long userId = 5L;
	Long accommodationId = 4L;

	@Test
	@DisplayName("Register bookmark - success")
	void registerBookmarkSuccess() throws Exception {
		//given
		BookmarkRegistrationRequests bookmarkRegistrationRequests = new BookmarkRegistrationRequests(accommodationId,
			userId);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(post("/bookmark")
			.content(objectMapper.writeValueAsString(bookmarkRegistrationRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(
				document("bookmark-registration",
					requestFields(
						fieldWithPath("userId").type(JsonFieldType.NUMBER).description("bookmark를 등록하고자 하는 user의 아이디"),
						fieldWithPath("accommodationId").type(JsonFieldType.NUMBER)
							.description("bookmark 하고자 하는 accommodation의 아이디")
					),
					responseFields(
						fieldWithPath("userId").type(JsonFieldType.NUMBER).description("등록한 user의 아이디"),
						fieldWithPath("accommodationId").type(JsonFieldType.NUMBER)
							.description("등록한 accommodation의 아이디"),
						fieldWithPath("state").type(JsonFieldType.BOOLEAN)
							.description("등록한 bookmark의 상태 false : 정상, true : 삭제")
					))).andDo(print());
	}

	@Test
	@DisplayName("Register bookmark after deleted (update state) - success")
	void registerBookmarkAfterDeletedSuccess() throws Exception {
		//given
		Long accommodationId = 3L;
		BookmarkRegistrationRequests bookmarkRegistrationRequests = new BookmarkRegistrationRequests(accommodationId,
			userId);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(post("/bookmark")
			.content(objectMapper.writeValueAsString(bookmarkRegistrationRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(
				document("bookmark-registration",
					requestFields(
						fieldWithPath("userId").type(JsonFieldType.NUMBER).description("bookmark를 등록하고자 하는 user의 아이디"),
						fieldWithPath("accommodationId").type(JsonFieldType.NUMBER)
							.description("bookmark 하고자 하는 accommodation의 아이디")
					),
					responseFields(
						fieldWithPath("userId").type(JsonFieldType.NUMBER).description("등록한 user의 아이디"),
						fieldWithPath("accommodationId").type(JsonFieldType.NUMBER)
							.description("등록한 accommodation의 아이디"),
						fieldWithPath("state").type(JsonFieldType.BOOLEAN)
							.description("등록한 bookmark의 상태 false : 정상, true : 삭제")
					))).andDo(print());
	}

	@Test
	@DisplayName("Delete bookmark - success")
	void deleteBookmarkSuccess() throws Exception {
		//given
		Long accommodationId = 2L;
		DeleteBookmarkRequests deleteBookmarkRequests = new DeleteBookmarkRequests(accommodationId, userId);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(delete("/bookmark/{accommodation-id}", accommodationId)
			.content(objectMapper.writeValueAsString(deleteBookmarkRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(
				document("bookmark-delete",
					pathParameters(
						parameterWithName("accommodation-id").description("bookmark 취소할 accommodation 아이디")
					),
					requestFields(
						fieldWithPath("userId").type(JsonFieldType.NUMBER).description("취소할 user 아이디"),
						fieldWithPath("accommodationId").type(JsonFieldType.NUMBER).description("취소할 accommodation 아이디")
					),
					responseFields(
						fieldWithPath("userId").type(JsonFieldType.NUMBER).description("삭제한 user의 아이디"),
						fieldWithPath("accommodationId").type(JsonFieldType.NUMBER)
							.description("삭제한 accommodation의 아이디"),
						fieldWithPath("state").type(JsonFieldType.BOOLEAN)
							.description("삭제한 bookmark의 상태 false : 정상, true : 삭제")
					)
				)).andDo(print());
	}
}
