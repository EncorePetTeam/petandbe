package com.encore.petandbe.service.accommodation.room;

import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.room.requests.RoomRegistrationRequest;
import com.encore.petandbe.controller.accommodation.room.requests.RoomUpdatingequest;
import com.encore.petandbe.controller.accommodation.room.responses.RoomRetrievalResponse;

@Service
public class RoomService {
	public Long createRoom(RoomRegistrationRequest request) {
		return null;
	}

	public Long updateRoom(RoomUpdatingequest request, Long roomId) {
		return null;
	}

	public Long deleteRoomById(Long roomId) {
		return null;
	}

	public RoomRetrievalResponse findRoomById(Long roomId) {
		return null;
	}
}
