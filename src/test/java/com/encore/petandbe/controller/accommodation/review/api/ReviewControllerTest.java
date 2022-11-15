package com.encore.petandbe.controller.accommodation.review.api;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
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
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.encore.petandbe.controller.accommodation.review.requests.DeleteReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.UpdateReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.service.accommodation.review.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ReviewController.class)
@AutoConfigureRestDocs
class ReviewControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReviewService reviewService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Regist review - success")
	void registReviewSuccess() throws Exception {
		//given
		Long reviewId = 1L;
		String userId = "token123";
		Integer rate = 5;
		String content = "very good";

		RegistReviewRequests registReviewRequests = new RegistReviewRequests("token123", 5, "very good", 1L);
		ReviewDetailsResponse reviewDetailsResponse = new ReviewDetailsResponse(reviewId, userId, rate, content);

		when(reviewService.registReview(any(RegistReviewRequests.class))).thenReturn(reviewDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.post("/review")
			.content(objectMapper.writeValueAsString(registReviewRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(document("regist-review",
				responseFields(
					fieldWithPath("reviewId").type(JsonFieldType.NUMBER).description("작성한 리뷰의 Id"),
					fieldWithPath("userId").type(JsonFieldType.STRING).description("작성한 User의 token id"),
					fieldWithPath("rate").type(JsonFieldType.NUMBER).description("부여한 점수 rate"),
					fieldWithPath("content").type(JsonFieldType.STRING).description("작성한 리뷰의 내용")
				))).andDo(print());
	}

	@Test
	@DisplayName("Get Review Details - success")
	void reviewDetailsSuccess() throws Exception {
		//given
		Long reviewId = 1L;
		String userId = "token123";
		Integer rate = 5;
		String content = "very good";

		ReviewDetailsResponse reviewDetailsResponse = new ReviewDetailsResponse(reviewId, userId, rate, content);

		when(reviewService.findReviewDetails(anyString())).thenReturn(reviewDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.get("/review/{reservation-id}", "1")
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("review-details",
				pathParameters(
					parameterWithName("reservation-id").description("Reservation의 ID")
				),
				responseFields(
					fieldWithPath("reviewId").type(JsonFieldType.NUMBER).description("Review의 Id"),
					fieldWithPath("userId").type(JsonFieldType.STRING).description("Review를 작성한 User의 Id"),
					fieldWithPath("rate").type(JsonFieldType.NUMBER).description("점수"),
					fieldWithPath("content").type(JsonFieldType.STRING).description("리뷰 본문")
				)
			)).andDo(print());
	}

	@Test
	@DisplayName("Update review - success")
	void updateReviewSuccess() throws Exception {
		//given
		Long reviewId = 1L;
		String userId = "token123";
		Integer rate = 3;
		String content = "not bad";

		UpdateReviewRequests updateReviewRequests = new UpdateReviewRequests(reviewId, userId, rate, content);
		ReviewDetailsResponse reviewDetailsResponse = new ReviewDetailsResponse(reviewId, userId, rate, content);

		when(reviewService.updateReview(any(UpdateReviewRequests.class))).thenReturn(reviewDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.put("/review")
			.content(objectMapper.writeValueAsString(updateReviewRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("update-review",
				responseFields(
					fieldWithPath("reviewId").type(JsonFieldType.NUMBER).description("수정한 리뷰의 Id"),
					fieldWithPath("userId").type(JsonFieldType.STRING).description("User의 token id"),
					fieldWithPath("rate").type(JsonFieldType.NUMBER).description("수정한 점수 rate"),
					fieldWithPath("content").type(JsonFieldType.STRING).description("수정한 리뷰의 내용")
				))).andDo(print());
	}

	@Test
	@DisplayName("Delete review - success")
	void deleteReviewSuccess() throws Exception {
		//given
		Long reviewId = 1L;
		String state = "1";

		DeleteReviewRequests deleteReviewRequests = new DeleteReviewRequests(reviewId, "token123");
		DeleteReviewResponse deleteReviewResponse = new DeleteReviewResponse(reviewId, state);

		when(reviewService.deleteReview(any(DeleteReviewRequests.class))).thenReturn(deleteReviewResponse);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.delete("/review")
			.content(objectMapper.writeValueAsString(deleteReviewRequests))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("delete-review",
				responseFields(
					fieldWithPath("reviewId").type(JsonFieldType.NUMBER).description("삭제한 리뷰의 Id"),
					fieldWithPath("state").type(JsonFieldType.STRING).description("삭제한 리뷰의 상태값")
				))).andDo(print());
	}
}
