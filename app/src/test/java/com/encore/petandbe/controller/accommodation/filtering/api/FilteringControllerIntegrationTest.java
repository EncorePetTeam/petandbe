package com.encore.petandbe.controller.accommodation.filtering.api;

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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FilteringControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Filtering accommodation controller - success")
	void filteringAccommodationSuccess() throws Exception {
		//given
		MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
		info.add("address", "05300");
		info.add("checkIn", "2022-11-15 16:00:00");
		info.add("checkOut", "2022-11-18 11:00:00");
		info.add("petCategory", "DOG");
		info.add("weight", "4.9");
		info.add("sortCategory", "AVERAGE");
		info.add("page", "1");

		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.get("/filtering/accommodation")
			.params(info)
			.accept(MediaType.APPLICATION_JSON));
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
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.get("/filtering/accommodation")
			.accept(MediaType.APPLICATION_JSON));
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
