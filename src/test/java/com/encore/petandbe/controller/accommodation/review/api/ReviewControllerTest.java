package com.encore.petandbe.controller.accommodation.review.api;

import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.service.accommodation.review.ReviewService;
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
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReviewController.class)
@AutoConfigureRestDocs
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    @DisplayName("리뷰 등록 테스트 - 정상")
    void reviewRegistSuccess() throws Exception {
        //given
        String reviewId = "1";
        String userId = "token123";
        String rate = "5";
        String content = "very good";

        ReviewDetailsResponse reviewDetailsResponse = new ReviewDetailsResponse(reviewId,userId,rate,content);

        when(reviewService.registReview(any(RegistReviewRequests.class))).thenReturn(reviewDetailsResponse);
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .post("/review/regist-review")
                .content("{\"userId\": \"token123\", \n\"rate\": \"5\", \n\"content\": \"very good\", \n\"reservationId\": 1}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk())
                .andDo(document("regist-review",
                        responseFields(
                            fieldWithPath("reviewId").type(JsonFieldType.STRING).description("작성한 리뷰의 Id"),
                            fieldWithPath("userId").type(JsonFieldType.STRING).description("작성한 User의 token id"),
                            fieldWithPath("rate").type(JsonFieldType.STRING).description("부여한 점수 rate"),
                            fieldWithPath("content").type(JsonFieldType.STRING).description("작성한 리뷰의 내용")
                ))).andDo(print());
    }
}
