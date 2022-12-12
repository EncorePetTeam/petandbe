package com.encore.petandbe.service.accommodation.room;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.room.requests.RoomRegistrationRequest;
import com.encore.petandbe.controller.accommodation.room.responses.RoomRetrievalResponse;
import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
import com.encore.petandbe.model.accommodation.room.Room;
import com.encore.petandbe.repository.RoomRepository;
import com.encore.petandbe.service.accommodation.room.dto.RoomUpdateDTO;

@SpringBootTest
@Transactional
class RoomServiceTest {

	@Autowired
	private RoomService roomService;

	@Autowired
	private RoomRepository roomRepository;

	private final Long accommodationId = 1L;

	private final String roomName = "해피해피";

	private final Integer amount = 50000;

	private final PetCategory petCategory = PetCategory.DOG;

	private final String weight = "10";

	private final String detailInfo = "물기 금지";

	@Test
	@DisplayName("Create Room Service - Success")
	void createRoom() {
		//given
		RoomRegistrationRequest request = new RoomRegistrationRequest(
			accommodationId, roomName, amount, petCategory, weight, detailInfo
		);
		//when
		Long roomId = roomService.createRoom(request);
		Optional<Room> room = roomRepository.findById(roomId);
		//then
		assertThat(room).isNotNull();
	}

	@Test
	@DisplayName("Update")
	void updateRoom() {
		//given
		Room room = roomRepository.findById(1L).get();
		//when
		room.updateRoom(RoomUpdateDTO.builder()
			.roomName(roomName)
			.amount(amount)
			.petCategory(petCategory)
			.weight(weight)
			.detailInfo(detailInfo)
			.build()
		);
		//then
		assertThat(room.getRoomName()).isEqualTo(roomName);
		assertThat(room.getAmount()).isEqualTo(amount);
		assertThat(room.getPetCategory()).isEqualTo(petCategory);
		assertThat(room.getWeight()).isEqualTo(weight);
		assertThat(room.getDetailInfo()).isEqualTo(detailInfo);
	}

	@Test
	@DisplayName("Delete Room Service - Success")
	void deleteRoomById() {
		//given
		Room room = roomRepository.findById(1L).get();
		//when
		room.deleteRoom();
		//then
		assertThat(room.getState()).isTrue();
	}

	@Test
	@DisplayName("Find Room Test - Success")
	void findRoomById() {
		//given//when
		RoomRetrievalResponse room = roomService.findRoomById(1L);
		//then
		assertThat(room).isNotNull();
	}
}