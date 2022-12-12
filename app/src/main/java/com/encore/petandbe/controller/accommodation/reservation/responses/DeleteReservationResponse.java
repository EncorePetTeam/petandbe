package com.encore.petandbe.controller.accommodation.reservation.responses;

import lombok.Getter;

@Getter
public class DeleteReservationResponse {

	private Long reservationId;
	private boolean state;

	public DeleteReservationResponse(Long reservationId, boolean state) {
		this.reservationId = reservationId;
		this.state = state;
	}
}
