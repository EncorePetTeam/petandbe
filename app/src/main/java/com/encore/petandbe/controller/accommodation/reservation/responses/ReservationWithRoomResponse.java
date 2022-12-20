package com.encore.petandbe.controller.accommodation.reservation.responses;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ReservationWithRoomResponse {

	private final Long roomId;
	private final Long accommodationId;
	private final LocalDateTime checkInDate;
	private final LocalDateTime checkOutDate;
	private final Integer amount;

	public ReservationWithRoomResponse(Long roomId, Long accommodationId, LocalDateTime checkInDate,
		LocalDateTime checkOutDate, Integer amount) {
		this.roomId = roomId;
		this.accommodationId = accommodationId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.amount = amount;
	}
}
