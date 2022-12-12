package com.encore.petandbe.controller.accommodation.reservation.requests;

import lombok.Getter;

@Getter
public class DeleteReservationRequest {

	private Long userId;
	private Long reservationId;

	public DeleteReservationRequest(Long userId, Long reservationId) {
		this.userId = userId;
		this.reservationId = reservationId;
	}
}
