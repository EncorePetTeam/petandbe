package com.encore.petandbe.controller.user.host.api;

import com.encore.petandbe.interceptor.PermissionInterceptor;
import com.encore.petandbe.model.user.user.Role;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.encore.petandbe.controller.user.host.requests.HostRegistrationRequest;
import com.encore.petandbe.service.user.host.HostService;

@WebMvcTest(controllers = HostController.class)
@AutoConfigureRestDocs
class HostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HostService hostService;

	@MockBean
	private PermissionInterceptor permissionInterceptor;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Register Host Controller - Success")
	void registerHostSuccess() throws Exception {
		//given
		Long userId = 1L;
		String hostName = "김훈기";
		String registrationNumber = "123456789012";
		String openDate = "19960521";

		HostRegistrationRequest hostRegistrationRequest = new HostRegistrationRequest(registrationNumber, hostName,
			openDate);

		when(hostService.createHost(hostRegistrationRequest, userId)).thenReturn(userId);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(
			post("/host")
				.content(objectMapper.writeValueAsString(hostRegistrationRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(print())
			.andDo(document("host-register",
				requestFields(
					fieldWithPath("hostName").type(JsonFieldType.STRING).description("사업장에 등록된 호스트 이름(대표자 이름)"),
					fieldWithPath("registrationNumber").type(JsonFieldType.STRING).description("사업장 등록 번호"),
					fieldWithPath("openDate").type(JsonFieldType.STRING).description("사업 개장 날짜")
				),
				responseFields(
					fieldWithPath("hostId").type(JsonFieldType.NUMBER).description("호스트로 등록된 호스트 id")
				)));
	}
}