package com.encore.petandbe.controller.user.user.api;

import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.service.user.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("유저 정보 찾기 테스트 - 정상")
    void userDetailsSuccess() throws Exception {
        //given
        String token = "token123";
        String email = "gsnrl@naver.com";
        String nickname = "rnfjddl";

        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(token, email, nickname);

        when(userService.findUserDetails(token)).thenReturn(userDetailsResponse);
        //when
        ResultActions resultActions = mockMvc.perform(get("/user/{token}", token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpectAll(
                        status().isOk()
                )
                .andDo(document("user-details",
                        requestParameters(
                                parameterWithName("token").description("OAuth의 token id").getAttributes()
                        ),
                        responseFields(
                                fieldWithPath("token").description("OAuth의 token id"),
                                fieldWithPath("email").description("유저이메일"),
                                fieldWithPath("nickname").description("유저닉네임")
                        )
                )).andDo(print());

    }
}