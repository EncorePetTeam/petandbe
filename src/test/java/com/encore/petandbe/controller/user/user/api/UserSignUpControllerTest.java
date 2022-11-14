package com.encore.petandbe.controller.user.user.api;

import com.encore.petandbe.controller.user.user.responses.UserSignUpResponse;
import com.encore.petandbe.service.user.user.UserSignUpService;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserSignUpController.class)
@AutoConfigureRestDocs
class UserSignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserSignUpService userSignUpService;

    @Test
    @DisplayName("유저 회원 가입 테스트 - 정상")
    void userSignUp() throws Exception {
        //given
        String token = "token123";
        String email = "gnsrl@naver.com";
        String nickname = "rnfjddl";

        UserSignUpResponse userSignUpResponse = new UserSignUpResponse(token, email, nickname);

        when(userSignUpService.userSignUp(any())).thenReturn(userSignUpResponse);
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .post("/user/signup")
                .content(String.format("{\"token\": \"%s\", \"email\": \"%s\", \"nickname\": \"%s\"}", token, email, nickname))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isCreated())
                .andDo(document("user-signup",
                        requestFields(
                                fieldWithPath("token").type(JsonFieldType.STRING).description("OAuth의 token id"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("유저이메일"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저닉네임")
                        ),
                        responseFields(
                                fieldWithPath("token").type(JsonFieldType.STRING).description("OAuth의 token id"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("유저이메일"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저닉네임")
                        )
                )).andDo(print());
    }
}