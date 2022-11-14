package com.encore.petandbe.controller.accommodation.review.api;

import com.encore.petandbe.controller.accommodation.review.requests.GetReviewListByUserIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.GetReviewListByUserIdResponse;
import com.encore.petandbe.service.accommodation.review.ReviewGetListService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReviewGetListController.class)
@AutoConfigureRestDocs
class ReviewGetListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewGetListService reviewGetListService;

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

        GetReviewListByUserIdResponse getReviewListByUserIdResponse
                = new GetReviewListByUserIdResponse(endPage,startPage,nextPageExist,prevPageExist,selectPage,amountItem);

        when(reviewGetListService.getReviewListByUserId(any(GetReviewListByUserIdRequests.class))).thenReturn(getReviewListByUserIdResponse);
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .post("/review-list/get-review-list-by-userid")
                .content("{\"userId\": \"token123\", \n\"pageNum\": 3, \n\"amount\": 10}")
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