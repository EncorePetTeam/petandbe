package com.encore.petandbe.controller.accommodation.review.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
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
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.encore.petandbe.controller.accommodation.review.requests.ReviewListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByUserIdResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewWithAccommodationResponse;
import com.encore.petandbe.interceptor.PermissionInterceptor;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.review.ReviewGetListService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ReviewGetListController.class)
@AutoConfigureRestDocs
class ReviewGetListControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReviewGetListService reviewGetListService;

	@MockBean
	private PermissionInterceptor permissionInterceptor;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Get Review List By UserId - success")
	void getReviewListByUserId() throws Exception {
		//given
		int endPage = 13;
		int selectPage = 3;
		int amountItem = 10;

		String accommodationName = "정정일 애견 호텔";
		String reviewContent = "Very good ";

		List<ReviewWithAccommodationResponse> reviewWithAccommodationResponsesList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			reviewWithAccommodationResponsesList.add(new ReviewWithAccommodationResponse(accommodationName + i, (long)i,
				(long)i, 5, reviewContent + i));
		}

		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("userId", "1");
		param.add("pageNum", "3");
		param.add("amount", "10");

		ReviewListGetByUserIdResponse reviewListGetByUserIdResponse
			= new ReviewListGetByUserIdResponse(endPage, selectPage, amountItem, reviewWithAccommodationResponsesList);

		when(reviewGetListService.getReviewListByUserId(any(ReviewListGetByUserIdRequests.class))).thenReturn(
			reviewListGetByUserIdResponse);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.get("/review-list")
			.params(param)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), 1)
		);
		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("get-review-list-by-userid",
				requestParameters(
					parameterWithName("userId").description("user 아이디"),
					parameterWithName("pageNum").description("페이지 번호"),
					parameterWithName("amount").description("요청할 데이터 개수")
				),
				responseFields(
					fieldWithPath("endPage").type(JsonFieldType.NUMBER).description("페이지네이션의 끝번호"),
					fieldWithPath("selectPage").type(JsonFieldType.NUMBER).description("현재 조회하는 페이지"),
					fieldWithPath("amountItem").type(JsonFieldType.NUMBER).description("데이터 개수"),
					fieldWithPath("reviewWithAccommodationResponsesList").type(JsonFieldType.ARRAY)
						.description("리뷰 조회 결과 List"),
					fieldWithPath("reviewWithAccommodationResponsesList[].accommodationId").type(JsonFieldType.NUMBER)
						.description("숙소 Id"),
					fieldWithPath("reviewWithAccommodationResponsesList[].accommodationName").type(JsonFieldType.STRING)
						.description("숙소 이름"),
					fieldWithPath("reviewWithAccommodationResponsesList[].reservationId").type(JsonFieldType.NUMBER)
						.description("예약 Id"),
					fieldWithPath("reviewWithAccommodationResponsesList[].reviewRate").type(JsonFieldType.NUMBER)
						.description("부여한 평점"),
					fieldWithPath("reviewWithAccommodationResponsesList[].reviewContent").type(JsonFieldType.STRING)
						.description("User가 작성한 리뷰 내용")
				))).andDo(print());
	}
}