package com.encore.petandbe.controller.accommodation.room.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.accommodation.room.requests.RoomRegistrationRequest;
import com.encore.petandbe.controller.accommodation.room.requests.RoomUpdatingequest;
import com.encore.petandbe.controller.accommodation.room.responses.RoomIdResponse;
import com.encore.petandbe.controller.accommodation.room.responses.RoomRetrievalResponse;
import com.encore.petandbe.service.accommodation.room.RoomService;

@RestController
@RequestMapping("room")
public class RoomController {

	private final RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping
	public ResponseEntity<RoomIdResponse> registerRoom(@RequestBody RoomRegistrationRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new RoomIdResponse(roomService.createRoom(request)));
	}

	@PutMapping("{room-id}")
	public ResponseEntity<RoomIdResponse> updateRoom(@PathVariable("room-id") Long roomId,
		@RequestBody RoomUpdatingequest request) {
		return ResponseEntity.ok().body(new RoomIdResponse(roomService.updateRoom(request, roomId)));
	}

	@DeleteMapping("{room-id}")
	public ResponseEntity<RoomIdResponse> deleteRoom(@PathVariable("room-id") Long roomId) {
		return ResponseEntity.ok().body(new RoomIdResponse(roomService.deleteRoomById(roomId)));
	}

	@GetMapping("{room-id}")
	public ResponseEntity<RoomRetrievalResponse> retrieveRoom(@PathVariable("room-id") Long roomId) {
		return ResponseEntity.ok().body(roomService.findRoomById(roomId));
	}
}
