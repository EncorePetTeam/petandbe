package com.encore.petandbe.controller.accommodation.room.responses;

import lombok.Getter;

@Getter
public class RoomIdResponse {

	private Long roomId;

	public RoomIdResponse(Long roomId) {
		this.roomId = roomId;
	}
}
