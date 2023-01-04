package com.encore.petandbe.controller.accommodation.filtering.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.encore.petandbe.controller.accommodation.filtering.requests.FilteringAccommodationRequests;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationListResponse;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationResponse;
import com.encore.petandbe.interceptor.PermissionInterceptor;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.filtering.FilteringService;

@WebMvcTest(controllers = FilteringController.class)
@AutoConfigureRestDocs
class FilteringControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FilteringService filteringService;

	@MockBean
	private PermissionInterceptor permissionInterceptor;

	private Long userId = 1L;
	private String imageUrl = "imageUrl";

	@Test
	@DisplayName("Filtering accommodation controller - success")
	void filteringAccommodationSuccess() throws Exception {
		//given
		Long accommodationId = 1L;
		String responseAccommodationName = "정정일 애견호텔 & 유치원";
		String addressCode = "서울특별시 중구";
		String location = "동호로";
		String lotNumber = "249";
		double avgRate = 4.8;

		MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
		info.add("address", "서울특별시 중구");
		info.add("checkIn", "2022-11-15 16:00:00");
		info.add("checkOut", "2022-11-18 11:00:00");
		info.add("petCategory", "Dog");
		info.add("weight", "4.9");
		info.add("sortCategory", "AVERAGE");
		info.add("page", "3");

		List<FilteringAccommodationResponse> filteringAccommodationResponses = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Long id = accommodationId + i;
			String accommodationName = responseAccommodationName + i;
			String address = addressCode + i;
			double rate = avgRate - (i / 10);

			FilteringAccommodationResponse filteringAccommodationResponse = new FilteringAccommodationResponse(id,
				accommodationName, address, location, lotNumber, rate, false, imageUrl, 25000);
			filteringAccommodationResponses.add(filteringAccommodationResponse);
		}

		FilteringAccommodationListResponse filteringAccommodationListResponse = new FilteringAccommodationListResponse(
			filteringAccommodationResponses);

		when(filteringService.filteringAccommodation(anyLong(), any(FilteringAccommodationRequests.class))).thenReturn(
			filteringAccommodationListResponse);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(get("/filtering/accommodation")
			.params(info)
			.accept(MediaType.APPLICATION_JSON).requestAttr(Role.USER.getValue(), 1));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("filtering-accommodation",
				requestParameters(
					parameterWithName("address").description("주소"),
					parameterWithName("checkIn").description("체크인 시간 날짜"),
					parameterWithName("checkOut").description("체크아웃 시간 날짜"),
					parameterWithName("petCategory").description("반려동물종"),
					parameterWithName("weight").description("반려동물 무게"),
					parameterWithName("sortCategory").description("정렬방법"),
					parameterWithName("page").description("페이지 번호")
				),
				responseFields(
					fieldWithPath("filteringAccommodationList").type(JsonFieldType.ARRAY).description("필터 결과 List"),
					fieldWithPath("filteringAccommodationList[].accommodationId").type(JsonFieldType.NUMBER)
						.description("숙소 Id"),
					fieldWithPath("filteringAccommodationList[].accommodationName").type(JsonFieldType.STRING)
						.description("숙소 이름"),
					fieldWithPath("filteringAccommodationList[].addressCode").type(JsonFieldType.STRING)
						.description("숙소 주소 코드"),
					fieldWithPath("filteringAccommodationList[].location").type(JsonFieldType.STRING)
						.description("숙소 주소 - 동"),
					fieldWithPath("filteringAccommodationList[].lotNumber").type(JsonFieldType.STRING)
						.description("숙소 주소 - 지번"),
					fieldWithPath("filteringAccommodationList[].bookmarked").type(JsonFieldType.BOOLEAN)
						.description("북마크 여부 false - X true - O"),
					fieldWithPath("filteringAccommodationList[].avgRate").type(JsonFieldType.NUMBER)
						.description("숙소 평점"),
					fieldWithPath("filteringAccommodationList[].imageUrl").type(JsonFieldType.STRING)
						.description("이미지 Url"),
					fieldWithPath("filteringAccommodationList[].roomAmount").type(JsonFieldType.NUMBER)
						.description("방 가격")
				)
			)).andDo(print());
	}

	@Test
	@DisplayName("No Filtering accommodation controller - success")
	void noFilteringAccommodationSuccess() throws Exception {
		//given
		Long accommodationId = 1L;
		String responseAccommodationName = "정정일 애견호텔 & 유치원";
		String addressCode = "서울특별시 중구";
		String location = "동호로";
		String lotNumber = "249";
		double avgRate = 4.8;

		List<FilteringAccommodationResponse> filteringAccommodationResponses = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Long id = accommodationId + i;
			String accommodationName = responseAccommodationName + i;
			String address = addressCode + i;
			double rate = avgRate - (i / 10);

			FilteringAccommodationResponse filteringAccommodationResponse = new FilteringAccommodationResponse(id,
				accommodationName, address, location, lotNumber, rate, false, imageUrl, 25000);
			filteringAccommodationResponses.add(filteringAccommodationResponse);
		}

		FilteringAccommodationListResponse filteringAccommodationListResponse = new FilteringAccommodationListResponse(
			filteringAccommodationResponses);

		when(filteringService.filteringAccommodation(anyLong(), any(FilteringAccommodationRequests.class))).thenReturn(
			filteringAccommodationListResponse);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(get("/filtering/accommodation")
			.accept(MediaType.APPLICATION_JSON).requestAttr(Role.USER.getValue(), 1));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("no-filtering-accommodation",
				responseFields(
					fieldWithPath("filteringAccommodationList").type(JsonFieldType.ARRAY).description("필터 결과 List"),
					fieldWithPath("filteringAccommodationList[].accommodationId").type(JsonFieldType.NUMBER)
						.description("숙소 Id"),
					fieldWithPath("filteringAccommodationList[].accommodationName").type(JsonFieldType.STRING)
						.description("숙소 이름"),
					fieldWithPath("filteringAccommodationList[].addressCode").type(JsonFieldType.STRING)
						.description("숙소 주소 코드"),
					fieldWithPath("filteringAccommodationList[].location").type(JsonFieldType.STRING)
						.description("숙소 주소 - 동"),
					fieldWithPath("filteringAccommodationList[].lotNumber").type(JsonFieldType.STRING)
						.description("숙소 주소 - 지번"),
					fieldWithPath("filteringAccommodationList[].bookmarked").type(JsonFieldType.BOOLEAN)
						.description("북마크 여부 false - X true - O"),
					fieldWithPath("filteringAccommodationList[].avgRate").type(JsonFieldType.NUMBER)
						.description("숙소 평점"),
					fieldWithPath("filteringAccommodationList[].imageUrl").type(JsonFieldType.STRING)
						.description("이미지 Url"),
					fieldWithPath("filteringAccommodationList[].roomAmount").type(JsonFieldType.NUMBER)
						.description("방 가격")
				)
			)).andDo(print());
	}
}