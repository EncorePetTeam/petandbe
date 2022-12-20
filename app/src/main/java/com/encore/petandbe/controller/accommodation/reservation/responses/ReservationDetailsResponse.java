package com.encore.petandbe.controller.accommodation.reservation.responses;

import lombok.Getter;

@Getter
public class ReservationDetailsResponse {

	private Long reservationId;
	private Long userId;
	private Long roomId;
	private String checkInDate;
	private String checkOutDate;
	private Integer amount;

	public ReservationDetailsResponse(Long reservationId, Long userId, Long roomId, String checkInDate,
		String checkOutDate,
		Integer amount) {
		this.reservationId = reservationId;
		this.userId = userId;
		this.roomId = roomId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.amount = amount;
	}
}
