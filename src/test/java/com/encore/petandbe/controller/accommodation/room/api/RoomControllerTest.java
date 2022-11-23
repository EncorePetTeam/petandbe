package com.encore.petandbe.controller.accommodation.room.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.encore.petandbe.controller.accommodation.room.requests.RoomRegistrationRequest;
import com.encore.petandbe.controller.accommodation.room.requests.RoomUpdatingequest;
import com.encore.petandbe.controller.accommodation.room.responses.RoomRetrievalResponse;
import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
import com.encore.petandbe.service.accommodation.room.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = RoomController.class)
@AutoConfigureRestDocs
class RoomControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RoomService roomService;

	@Autowired
	private ObjectMapper objectMapper;

	Long accommodationId = 1L;
	String roomName = "훈기방";
	Integer amount = 30000;
	String weight = "10kg";
	String detailInfo = "안녕하세요";
	PetCategory petCategory = PetCategory.CAT;
	Long roomId = 1L;

	@Test
	void registerRoomSuccess() throws Exception {
		//given
		RoomRegistrationRequest roomRegistrationRequest = new RoomRegistrationRequest(accommodationId, roomName, amount,
			petCategory, weight, detailInfo);
		when(roomService.createRoom(roomRegistrationRequest)).thenReturn(roomId);
		//when
		ResultActions resultActions = mockMvc.perform(post("/room")
			.content(objectMapper.writeValueAsString(roomRegistrationRequest))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(print())
			.andDo(
				document("room-registration",
					requestFields(
						fieldWithPath("accommodationId").type(JsonFieldType.NUMBER).description("룸과 연관된 숙박시설 아이디"),
						fieldWithPath("roomName").type(JsonFieldType.STRING).description("룸 이름"),
						fieldWithPath("amount").type(JsonFieldType.NUMBER).description("룸 대여료"),
						fieldWithPath("weight").type(JsonFieldType.STRING).description("룸을 대여하는 펫의 무게 제한"),
						fieldWithPath("petCategory").type(JsonFieldType.STRING).description("룸을 대여하는 펫의 타입"),
						fieldWithPath("detailInfo").type(JsonFieldType.STRING).description("룸의 상세 설명")
					),
					responseFields(
						fieldWithPath("roomId").type(JsonFieldType.NUMBER).description("생성된 룸 아이디")
					)));
	}

	@Test
	void updateRoomSuccess() throws Exception {
		//given
		RoomUpdatingequest roomUpdatingequest = new RoomUpdatingequest(accommodationId, roomName, amount, petCategory,
			weight, detailInfo);

		when(roomService.updateRoom(roomUpdatingequest, roomId)).thenReturn(roomId);
		//when
		ResultActions resultActions = mockMvc.perform(put("/room/{room-id}", roomId)
			.content(objectMapper.writeValueAsString(roomUpdatingequest))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(
				document("room-updating",
					pathParameters(
						parameterWithName("room-id").description("수정할 룸 아이디")
					),
					requestFields(
						fieldWithPath("accommodationId").type(JsonFieldType.NUMBER).description("룸과 연관된 숙박시설 아이디"),
						fieldWithPath("roomName").type(JsonFieldType.STRING).description("수정 할 룸 이름"),
						fieldWithPath("amount").type(JsonFieldType.NUMBER).description("수정 할 룸 대여료"),
						fieldWithPath("weight").type(JsonFieldType.STRING).description("수정 할 펫의 무게 제한"),
						fieldWithPath("petCategory").type(JsonFieldType.STRING).description("수정 할 펫의 타입"),
						fieldWithPath("detailInfo").type(JsonFieldType.STRING).description("수정 할 룸의 상세 설명")
					),
					responseFields(
						fieldWithPath("roomId").type(JsonFieldType.NUMBER).description("수정 할 룸 아이디")
					)
				));
	}

	@Test
	void deleteRoomSuccess() throws Exception {
		//given
		when(roomService.deleteRoomById(roomId)).thenReturn(roomId);
		//when
		ResultActions resultActions = mockMvc.perform(delete("/room/{room-id}", roomId)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(
				document("room-deleting",
					pathParameters(
						parameterWithName("room-id").description("삭제할 룸 아이디")
					),
					responseFields(
						fieldWithPath("roomId").type(JsonFieldType.NUMBER).description("삭제된 룸 아이디")
					)));
	}

	@Test
	void retrieveRoomSuccess() throws Exception {
		//given
		RoomRetrievalResponse roomRetrievalResponse = new RoomRetrievalResponse(accommodationId, roomName, amount,
			petCategory, weight, detailInfo);

		when(roomService.findRoomById(roomId)).thenReturn(roomRetrievalResponse);
		//when
		ResultActions resultActions = mockMvc.perform(get("/room/{room-id}", roomId)
			.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("room-retrieval",
				pathParameters(
					parameterWithName("room-id").description("찾고싶은 룸의 아이디")
				),
				responseFields(
					fieldWithPath("accommodationId").type(JsonFieldType.NUMBER).description("룸과 연관된 숙박시설 아이디"),
					fieldWithPath("roomName").type(JsonFieldType.STRING).description("룸 이름"),
					fieldWithPath("amount").type(JsonFieldType.NUMBER).description("룸 대여료"),
					fieldWithPath("weight").type(JsonFieldType.STRING).description("룸을 대여하는 펫의 무게 제한"),
					fieldWithPath("petCategory").type(JsonFieldType.STRING).description("룸을 대여하는 펫의 타입"),
					fieldWithPath("detailInfo").type(JsonFieldType.STRING).description("룸의 상세 설명")
				)));
	}
}