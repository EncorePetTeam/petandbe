package com.encore.petandbe.controller.accommodation.bookmark.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

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

import com.encore.petandbe.controller.accommodation.bookmark.requests.BookmarkRegistrationRequests;
import com.encore.petandbe.controller.accommodation.bookmark.requests.DeleteBookmarkRequests;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkAccommodationListResponse;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkAccommodationResponse;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkDetailsResponse;
import com.encore.petandbe.service.accommodation.bookmark.BookmarkService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = BookmarkController.class)
@AutoConfigureRestDocs
class BookmarkControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookmarkService bookmarkService;

	@Autowired
	private ObjectMapper objectMapper;

	Long userId = 1L;
	Long accommodationId = 2L;

	@Test
	@DisplayName("Register bookmark - success")
	void registerBookmarkSuccess() throws Exception {
		//given
		BookmarkRegistrationRequests bookmarkRegistrationRequests = new BookmarkRegistrationRequests(userId,
			accommodationId);
		BookmarkDetailsResponse bookmarkDetailsResponse = new BookmarkDetailsResponse(userId, accommodationId, false);
		when(bookmarkService.registBookmark(any(BookmarkRegistrationRequests.class))).thenReturn(
			bookmarkDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(post("/bookmark")
			.content(objectMapper.writeValueAsString(bookmarkRegistrationRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
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
		DeleteBookmarkRequests deleteBookmarkRequests = new DeleteBookmarkRequests(userId, accommodationId);
		BookmarkDetailsResponse bookmarkDetailsResponse = new BookmarkDetailsResponse(userId, accommodationId, true);

		when(bookmarkService.deleteBookmark(any(DeleteBookmarkRequests.class))).thenReturn(bookmarkDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(delete("/bookmark/{accommodation-id}", accommodationId)
			.content(objectMapper.writeValueAsString(deleteBookmarkRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
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

	@Test
	@DisplayName("Get accommodationList by bookmark - success")
	void accommodationListByBookmarkSuccess() throws Exception {
		//given
		String accommodationName = "정정일 애견 호텔 ";
		String address = "05300";
		double avgRate = 4.3;

		List<BookmarkAccommodationResponse> bookmarkAccommodationResponseList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			bookmarkAccommodationResponseList.add(
				new BookmarkAccommodationResponse(accommodationId + i, accommodationName + i, address, avgRate));
		}
		BookmarkAccommodationListResponse bookmarkAccommodationListResponse = new BookmarkAccommodationListResponse(
			userId, bookmarkAccommodationResponseList);
		when(bookmarkService.findAccommodationListByBookmark(userId)).thenReturn(bookmarkAccommodationListResponse);
		//when
		ResultActions resultActions = mockMvc.perform(get("/bookmark/{user-id}", userId)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(
				document("bookmark-get-list",
					pathParameters(
						parameterWithName("user-id").description("bookmark 목록을 조회할 user 아이디")
					),
					responseFields(
						fieldWithPath("userId").type(JsonFieldType.NUMBER).description("조회한 user의 아이디"),
						fieldWithPath("bookmarkAccommodationResponseList").type(JsonFieldType.ARRAY)
							.description("조회 결과 accommodation list"),
						fieldWithPath("bookmarkAccommodationResponseList[].accommodationId").type(JsonFieldType.NUMBER)
							.description("accommodation의 아이디"),
						fieldWithPath("bookmarkAccommodationResponseList[].accommodationName").type(
							JsonFieldType.STRING).description("accommodation의 이름"),
						fieldWithPath("bookmarkAccommodationResponseList[].avgRate").type(JsonFieldType.NUMBER)
							.description("accommodation의 평점"),
						fieldWithPath("bookmarkAccommodationResponseList[].address").type(JsonFieldType.STRING)
							.description("accommodation의 주소 코드")
					)
				)).andDo(print());
	}
}