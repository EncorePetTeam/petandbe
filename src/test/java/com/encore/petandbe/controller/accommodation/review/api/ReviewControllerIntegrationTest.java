package com.encore.petandbe.controller.accommodation.review.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Transactional
class ReviewControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Get review list page - success")
	void getReviewListPage() throws Exception {
		//given
		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("userId", "5");
		param.add("pageNum", "1");
		param.add("amount", "10");

		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.get("/review-list")
			.params(param)
			.accept(MediaType.APPLICATION_JSON));
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

	@Test
	@DisplayName("Get review list page out of length - success")
	void getReviewListPageFail() throws Exception {
		//given
		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("userId", "5");
		param.add("pageNum", "2");
		param.add("amount", "10");

		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.get("/review-list")
			.params(param)
			.accept(MediaType.APPLICATION_JSON));
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
						.description("리뷰 조회 결과 List")
				))).andDo(print());
	}
}
