package com.encore.petandbe.utils.mapper;

import com.encore.petandbe.controller.accommodation.room.requests.RoomRegistrationRequest;
import com.encore.petandbe.controller.accommodation.room.requests.RoomUpdateRequest;
import com.encore.petandbe.controller.accommodation.room.responses.RoomRetrievalResponse;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.room.Room;
import com.encore.petandbe.service.accommodation.room.dto.RoomUpdateDTO;

public class RoomMapper {

	private static RoomMapper roomMapper = null;

	public static RoomMapper of() {
		if (roomMapper == null){
			roomMapper = new RoomMapper();
		}
		return roomMapper;
	}

	public Room createRoomRequestToEntity(RoomRegistrationRequest roomRegistrationRequest, Accommodation accommodation){
		return Room.builder().accommodation(accommodation)
			.roomName(roomRegistrationRequest.getRoomName())
			.amount(roomRegistrationRequest.getAmount())
			.petCategory(roomRegistrationRequest.getPetCategory())
			.weight(roomRegistrationRequest.getWeight())
			.detailInfo(roomRegistrationRequest.getDetailInfo())
			.build();
	}

	public static RoomUpdateDTO convertUpdatingRequestToUpdatingDTO(RoomUpdateRequest roomUpdateRequest){
		return RoomUpdateDTO.builder()
			.roomName(roomUpdateRequest.getRoomName())
			.amount(roomUpdateRequest.getAmount())
			.petCategory(roomUpdateRequest.getPetCategory())
			.weight(roomUpdateRequest.getWeight())
			.detailInfo(roomUpdateRequest.getDetailInfo())
			.build();
	}

	public static RoomRetrievalResponse convertRoomToRetrievalResponse(Room room){
		return RoomRetrievalResponse.builder()
			.accommodationId(room.getAccommodation().getId())
			.roomName(room.getRoomName())
			.amount(room.getAmount())
			.petCategory(room.getPetCategory())
			.weight(room.getWeight())
			.detailInfo(room.getDetailInfo())
			.build();
	}
}

