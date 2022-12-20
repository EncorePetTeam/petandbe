package com.encore.petandbe.controller.accommodation.reservation.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
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

import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationListGetByUserIdResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationWithRoomResponse;
import com.encore.petandbe.interceptor.PermissionInterceptor;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.reservation.ReservationGetListService;

@WebMvcTest(controllers = ReservationGetListController.class)
@AutoConfigureRestDocs
class ReservationGetListControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReservationGetListService reservationGetListService;

	@MockBean
	private PermissionInterceptor permissionInterceptor;

	@Test
	@DisplayName("Get Reservation List By UserId - Sueccess")
	void getReservationListByUserId() throws Exception {
		//given
		int endPage = 13;
		int selectPage = 3;
		int amountItem = 10;

		Long userId = 1L;

		LocalDateTime checkInDate = LocalDateTime.parse("2022-11-17T11:00:00");
		LocalDateTime checkOutDate = LocalDateTime.parse("2022-11-19T16:00:00");
		Integer amount = 100000;

		List<ReservationWithRoomResponse> reservationWithRoomResponseList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			reservationWithRoomResponseList.add(
				new ReservationWithRoomResponse((long)i, (long)i, checkInDate, checkOutDate, amount));
		}

		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("pageNum", "7");
		param.add("itemAmount", "20");

		ReservationListGetByUserIdResponse reservationListGetByUserIdResponse
			= new ReservationListGetByUserIdResponse(endPage, selectPage, amountItem, reservationWithRoomResponseList);

		when(reservationGetListService.getReservationListByUserId(
			any(ReservationListGetByUserIdRequests.class))).thenReturn(
			reservationListGetByUserIdResponse);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		//when
		ResultActions resultActions = mockMvc.perform(get("/reservation-list")
			.params(param)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(document("get-reservation-list-by-userid",
				requestParameters(
					parameterWithName("pageNum").description("페이지 번호"),
					parameterWithName("itemAmount").description("요청할 데이터 개수")
				),
				responseFields(
					fieldWithPath("endPage").type(JsonFieldType.NUMBER).description("페이지네이션의 끝번호"),
					fieldWithPath("selectPage").type(JsonFieldType.NUMBER).description("현재 조회하는 페이지"),
					fieldWithPath("amountItem").type(JsonFieldType.NUMBER).description("데이터 개수"),
					fieldWithPath("reservationWithRoomResponseList").type(JsonFieldType.ARRAY)
						.description("예약 조회 결과 List"),
					fieldWithPath("reservationWithRoomResponseList[].roomId").type(JsonFieldType.NUMBER)
						.description("예약했던 방 id"),
					fieldWithPath("reservationWithRoomResponseList[].accommodationId").type(JsonFieldType.NUMBER)
						.description("숙소 id"),
					fieldWithPath("reservationWithRoomResponseList[].checkInDate").type(JsonFieldType.STRING)
						.description("체크인 날짜 시간"),
					fieldWithPath("reservationWithRoomResponseList[].checkOutDate").type(JsonFieldType.STRING)
						.description("체크아웃 날짜 시간"),
					fieldWithPath("reservationWithRoomResponseList[].amount").type(JsonFieldType.NUMBER)
						.description("예약 금액")
				))).andDo(print());
	}
}