package com.encore.petandbe.controller.accommodation.reservation.requests;

import lombok.Getter;

@Getter
public class ReservationRegistrationRequest {

	private Long userId;
	private Long roomId;
	private String checkInDate;
	private String checkOutDate;
	private Integer amount;

	public ReservationRegistrationRequest(Long userId, Long roomId, String checkInDate, String checkOutDate,
		Integer amount) {
		this.userId = userId;
		this.roomId = roomId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.amount = amount;
	}
}
