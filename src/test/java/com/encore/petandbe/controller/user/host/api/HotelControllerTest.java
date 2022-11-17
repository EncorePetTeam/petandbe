package com.encore.petandbe.controller.user.host.api;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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

import com.encore.petandbe.controller.user.host.request.DeleteHotelRequest;
import com.encore.petandbe.controller.user.host.request.RegistHotelRequest;
import com.encore.petandbe.controller.user.host.request.UpdateHotelRequest;
import com.encore.petandbe.controller.user.host.response.DeleteHotelResponse;
import com.encore.petandbe.controller.user.host.response.RegistHotelResponse;
import com.encore.petandbe.controller.user.host.response.UpdateHotelResponse;
import com.encore.petandbe.service.accommodation.HotelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = HotelController.class)
@AutoConfigureRestDocs
class HotelControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HotelService hotelService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Regist Hotel - Success")
	void registHotel() throws Exception {
		//given
		String accommondation_name = "신라호텔";
		String weekday_working_hours = "09001500";
		String weekend_working_hours = "07001600";
		String hotel_location = "독산역";
		String accomoodation_type = "스위트룸";
		String detail = "고양이만가능";

		RegistHotelRequest registHotelRequest = new RegistHotelRequest("신라호텔", "09001500", "07001600", "독산역", "스위트룸",
			"고양이만가능");
		RegistHotelResponse registHotelResponse = new RegistHotelResponse(accommondation_name, weekday_working_hours,
			weekend_working_hours, hotel_location, accomoodation_type, detail);

		when(hotelService.registHotel(any(RegistHotelRequest.class))).thenReturn(registHotelResponse);
		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.post("/hotel/regist")
			.content(objectMapper.writeValueAsString(registHotelRequest))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(document("Regist-Hotel",
				requestFields(
					fieldWithPath("accommondation_name").description("호텔의 이름"),
					fieldWithPath("weekday_working_hours").description("평일 체크인 체크아웃시간"),
					fieldWithPath("weekend_working_hours").description("주말 체크인 체크아웃시간"),
					fieldWithPath("hotel_location").description("호텔 위치"),
					fieldWithPath("accomoodation_type").description("호텔의 타입"),
					fieldWithPath("detail").description("호텔이용시 상세 내용")
				),
				responseFields(
					fieldWithPath("accommondation_name").description("호텔의 이름"),
					fieldWithPath("weekday_working_hours").description("평일 체크인 체크아웃시간"),
					fieldWithPath("weekend_working_hours").description("주말 체크인 체크아웃시간"),
					fieldWithPath("hotel_location").description("호텔 위치"),
					fieldWithPath("accomoodation_type").description("호텔의 타입"),
					fieldWithPath("detail").description("호텔이용시 상세 내용")
				))).andDo(print());
	}

	@Test
	@DisplayName("Delete Hotel - Success")
	void deleteHotel() throws Exception {
		//given
		Long hotelId = 11L;
		String state = "1";

		DeleteHotelRequest deleteHotelRequest = new DeleteHotelRequest(11L, "1");
		DeleteHotelResponse deleteHotelResponse = new DeleteHotelResponse(hotelId, state);
		when(hotelService.deleteHotel(any(DeleteHotelRequest.class))).thenReturn(deleteHotelResponse);

		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.post("/hotel/delete")
			.content(objectMapper.writeValueAsString(deleteHotelRequest))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));

		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("Delete - Hotel",
				requestFields(
					fieldWithPath("hotelId").description("호스트의 Id"),
					fieldWithPath("state").description("호스트의 상태값")
				),
				responseFields(
					fieldWithPath("hotelId").description("호스트의 Id"),
					fieldWithPath("state").description("호스트의 상태값")
				))).andDo(print());
	}

	@Test
	@DisplayName("Update Hotel - Success")
	void updateHotel() throws Exception {
		//given
		String accommondation_name = "신라호텔수정됨";
		String weekday_working_hours = "09001502수정됨";
		String weekend_working_hours = "07001602수정됨";
		String hotel_location = "독산역수정됨";
		String accomoodation_type = "스위트룸수정됨";
		String detail = "고양이만가능수정됨";

		UpdateHotelRequest updateHotelRequest = new UpdateHotelRequest("신라호텔", "09001500", "07001600", "독산역", "스위트룸",
			"고양이만가능");
		UpdateHotelResponse updateHotelResponse = new UpdateHotelResponse(accommondation_name, weekday_working_hours,
			weekend_working_hours, hotel_location, accomoodation_type, detail);
		when(hotelService.updateHotel(any(UpdateHotelRequest.class))).thenReturn(updateHotelResponse);

		//when
		ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
			.post("/hotel/update")
			.content(objectMapper.writeValueAsString(updateHotelRequest))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));

		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("Update-Hotel",
				requestFields(
					fieldWithPath("accommondation_name").description("수정된 호텔의 이름"),
					fieldWithPath("weekday_working_hours").description("수정된 평일 체크인 체크아웃시간"),
					fieldWithPath("weekend_working_hours").description("수정된 주말 체크인 체크아웃시간"),
					fieldWithPath("hotel_location").description("수정된 호텔 위치"),
					fieldWithPath("accomoodation_type").description("수정된 호텔의 타입"),
					fieldWithPath("detail").description("수정된 호텔 이용시 상세 내용")
				),
				responseFields(
					fieldWithPath("accommondation_name").description("수정된 호텔의 이름"),
					fieldWithPath("weekday_working_hours").description("수정된 평일 체크인 체크아웃시간"),
					fieldWithPath("weekend_working_hours").description("수정된 주말 체크인 체크아웃시간"),
					fieldWithPath("hotel_location").description("수정된 호텔 위치"),
					fieldWithPath("accomoodation_type").description("수정된 호텔의 타입"),
					fieldWithPath("detail").description("수정된 호텔 이용시 상세 내용")
				))).andDo(print());
	}
}