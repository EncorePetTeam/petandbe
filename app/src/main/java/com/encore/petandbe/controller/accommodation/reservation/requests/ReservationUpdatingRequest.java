package com.encore.petandbe.controller.accommodation.reservation.requests;

import lombok.Getter;

@Getter
public class ReservationUpdatingRequest {

	private Long userId;
	private String checkInDate;
	private String checkOutDate;
	private Integer amount;

	public ReservationUpdatingRequest(Long userId, String checkInDate, String checkOutDate, Integer amount) {
		this.userId = userId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.amount = amount;
	}
}
