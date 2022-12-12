package com.encore.petandbe.controller.accommodation.accommodation.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.accommodation.responses.AccommodationRetrievalResponse;
import com.encore.petandbe.interceptor.PermissionInterceptor;
import com.encore.petandbe.model.accommodation.accommodation.AccommodationType;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.accomodation.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AccommodationController.class)
@AutoConfigureRestDocs
class AccommodationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccommodationService accommodationService;

	@MockBean
	private PermissionInterceptor permissionInterceptor;

	@Autowired
	private ObjectMapper objectMapper;

	private final String addressCode = "11011";
	private final Long accommodationId = 1L;
	private final Long userId = 1L;
	private final String accommodationName = "훈기호텔";
	private final String addressDetail = "102호같은것";
	private final String workingHours = "09001800";
	private final String weekendWorkingHours = "10001800";
	private final String lotNumber = "202-1";
	private final String location = "상수동";
	private final AccommodationType accommodationType = AccommodationType.HOTEL;
	private final String detailInfo = "상세정보를 쓰는 공간입니다.";

	@Test
	@DisplayName("Register Accommodation Controller - Success")
	void registerAccommodationTestSuccess() throws Exception {
		//given
		AccommodationRegistrationRequest request = new AccommodationRegistrationRequest(addressCode,
			accommodationName, workingHours, weekendWorkingHours, location, lotNumber, addressDetail, accommodationType,
			detailInfo);
		when(accommodationService.createAccommodation(request, userId)).thenReturn(userId);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(
			post("/accommodation")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(print())
			.andDo(document("accommodation-register",
				requestFields(
					fieldWithPath("addressCode").type(JsonFieldType.STRING).description("숙박시설의 지역 코드"),
					fieldWithPath("accommodationName").type(JsonFieldType.STRING).description("숙박 시설 이름"),
					fieldWithPath("workingHours").type(JsonFieldType.STRING).description("평일 숙박 시설 영업 시간"),
					fieldWithPath("weekendWorkingHours").type(JsonFieldType.STRING).description("주말 숙박 시설 영업 시간"),
					fieldWithPath("location").type(JsonFieldType.STRING).description("동 이름"),
					fieldWithPath("lotNumber").type(JsonFieldType.STRING).description("숙박 시설 지번"),
					fieldWithPath("addressDetail").type(JsonFieldType.STRING).description("숙박 시설 상세 주소"),
					fieldWithPath("accommodationType").type(JsonFieldType.STRING).description("숙박 시설 유형"),
					fieldWithPath("detailInfo").type(JsonFieldType.STRING).description("숙박 시설 상세 정보")
				),
				responseFields(
					fieldWithPath("accommodationId").type(JsonFieldType.NUMBER).description("생성된 숙박 시설 아이디(pk)")
				)
			));
	}

	@Test
	@DisplayName("Retrieve Accommodation Controller - Success")
	void retrieveAccommodationTestSuccess() throws Exception {
		//given
		String userNickname = "gnsrl";
		Double averageRate = 4.2;
		AccommodationRetrievalResponse response = new AccommodationRetrievalResponse(addressCode, accommodationName,
			userNickname, workingHours, weekendWorkingHours, location, lotNumber, addressDetail, accommodationType,
			averageRate, detailInfo);

		when(accommodationService.findAccommodationById(accommodationId)).thenReturn(response);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(get("/accommodation/{accommodation-id}", accommodationId)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("accommodation-retrieval",
				pathParameters(parameterWithName("accommodation-id").description("찾고 싶은 숙박 시설 id")),
				responseFields(
					fieldWithPath("addressCode").type(JsonFieldType.STRING).description("숙박시설의 지역 코드"),
					fieldWithPath("userNickname").type(JsonFieldType.STRING).description("숙박시설 호스트 닉네임"),
					fieldWithPath("accommodationName").type(JsonFieldType.STRING).description("숙박 시설 이름"),
					fieldWithPath("workingHours").type(JsonFieldType.STRING).description("평일 숙박 시설 영업 시간"),
					fieldWithPath("weekendWorkingHours").type(JsonFieldType.STRING).description("주말 숙박 시설 영업 시간"),
					fieldWithPath("location").type(JsonFieldType.STRING).description("동 이름"),
					fieldWithPath("lotNumber").type(JsonFieldType.STRING).description("숙박 시설 지번"),
					fieldWithPath("addressDetail").type(JsonFieldType.STRING).description("숙박 시설 상세 주소"),
					fieldWithPath("accommodationType").type(JsonFieldType.STRING).description("숙박 시설 유형"),
					fieldWithPath("averageRate").type(JsonFieldType.NUMBER).description("숙박 시설 평점"),
					fieldWithPath("detailInfo").type(JsonFieldType.STRING).description("숙박 시설 상세 정보")
				)
			));
	}

	@Test
	@DisplayName("Update Accommodation Controller - Success")
	void updateAccommodationTestSuccess() throws Exception {
		//given
		AccommodationUpdatingRequest request = new AccommodationUpdatingRequest(addressCode,
			userId,
			accommodationName, workingHours, weekendWorkingHours, location, lotNumber, addressDetail, accommodationType,
			detailInfo);
		when(accommodationService.updateAccommodation(request, accommodationId)).thenReturn(accommodationId);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(put("/accommodation/{accommodation-id}", accommodationId)
			.content(objectMapper.writeValueAsString(request))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("accommodation-update",
				pathParameters(
					parameterWithName("accommodation-id").description("수정할 숙박시설 id")
				),
				requestFields(
					fieldWithPath("addressCode").type(JsonFieldType.STRING).description("숙박시설의 지역 코드"),
					fieldWithPath("userId").type(JsonFieldType.NUMBER).description("숙박 시설 호스트의 유저 아이디"),
					fieldWithPath("accommodationName").type(JsonFieldType.STRING).description("숙박 시설 이름"),
					fieldWithPath("workingHours").type(JsonFieldType.STRING).description("평일 숙박 시설 영업 시간"),
					fieldWithPath("weekendWorkingHours").type(JsonFieldType.STRING).description("주말 숙박 시설 영업 시간"),
					fieldWithPath("location").type(JsonFieldType.STRING).description("동 이름"),
					fieldWithPath("lotNumber").type(JsonFieldType.STRING).description("숙박 시설 지번"),
					fieldWithPath("addressDetail").type(JsonFieldType.STRING).description("숙박 시설 상세 주소"),
					fieldWithPath("accommodationType").type(JsonFieldType.STRING).description("숙박 시설 유형"),
					fieldWithPath("detailInfo").type(JsonFieldType.STRING).description("숙박 시설 상세 정보")
				),
				responseFields(
					fieldWithPath("accommodationId").type(JsonFieldType.NUMBER).description("수정된 숙박 시설 아이디(pk)")
				)
			));
	}

	@Test
	@DisplayName("Delete Accommodation Controller - Success")
	void deleteAccommodationTestSuccess() throws Exception {
		//given
		when(accommodationService.deleteAccommodationByStatus(accommodationId)).thenReturn(accommodationId);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(delete("/accommodation/{accommodation-id}", accommodationId)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("accommodation-delete",
				pathParameters(
					parameterWithName("accommodation-id").description("삭제할 숙박 시설 id")
				),
				responseFields(
					fieldWithPath("accommodationId").type(JsonFieldType.NUMBER).description("삭제된 숙박 시설 id")
				)
			));
	}
}