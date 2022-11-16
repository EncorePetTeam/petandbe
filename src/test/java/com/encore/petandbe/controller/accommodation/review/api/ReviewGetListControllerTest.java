package com.encore.petandbe.controller.accommodation.review.api;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import com.encore.petandbe.controller.accommodation.review.requests.GetReviewListByUserIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.GetReviewListByUserIdResponse;
import com.encore.petandbe.service.accommodation.review.ReviewGetListService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ReviewGetListController.class)
@AutoConfigureRestDocs
class ReviewGetListControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReviewGetListService reviewGetListService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Get Review List By UserId - success")
	void getReviewListByUserId() throws Exception {
		//given
		int endPage = 13;
		int startPage = 1;
		boolean nextPageExist = true;
		boolean prevPageExist = true;
		int selectPage = 3;
		int amountItem = 10;

		GetReviewListByUserIdRequests getReviewListByUserIdRequests
			= new GetReviewListByUserIdRequests("token123", selectPage, amountItem);
		GetReviewListByUserIdResponse getReviewListByUserIdResponse
			= new GetReviewListByUserIdResponse(endPage, startPage, nextPageExist, prevPageExist, selectPage,
			amountItem);

		when(reviewGetListService.getReviewListByUserId(any(GetReviewListByUserIdRequests.class))).thenReturn(
			getReviewListByUserIdResponse);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.post("/review-list/get-by-userid")
			.content(objectMapper.writeValueAsString(getReviewListByUserIdRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("get-review-list-by-userid",
				responseFields(
					fieldWithPath("endPage").type(JsonFieldType.NUMBER).description("페이지네이션의 끝번호"),
					fieldWithPath("startPage").type(JsonFieldType.NUMBER).description("페이지네이션의 시작번호"),
					fieldWithPath("nextPageExist").type(JsonFieldType.BOOLEAN).description("다음버튼 활성화 여부"),
					fieldWithPath("prevPageExist").type(JsonFieldType.BOOLEAN).description("이전버튼 황설화 여부"),
					fieldWithPath("selectPage").type(JsonFieldType.NUMBER).description("현재 조회하는 페이지"),
					fieldWithPath("amountItem").type(JsonFieldType.NUMBER).description("데이터 개수")
				))).andDo(print());
	}
}