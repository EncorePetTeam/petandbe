package com.encore.petandbe.controller.accommodation.reservation.responses;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;

import lombok.Getter;

@Getter
public class ReservationDetailsResponse {

	private Long reservationId;
	private Long userId;
	private Long roomId;
	private String checkInDate;
	private String checkOutDate;
	private PetCategory petCategory;
	private String weight;

	public ReservationDetailsResponse(Long reservationId, Long userId, Long roomId, String checkInDate,
		String checkOutDate,
		PetCategory petCategory, String weight) {
		this.reservationId = reservationId;
		this.userId = userId;
		this.roomId = roomId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.petCategory = petCategory;
		this.weight = weight;
	}
}
