package com.encore.petandbe.controller.accommodation.reservation.requests;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;

import lombok.Getter;

@Getter
public class ReservationUpdatingRequest {

	private Long userId;
	private String checkInDate;
	private String checkOutDate;
	private PetCategory petCategory;
	private String weight;

	public ReservationUpdatingRequest(Long userId, String checkInDate, String checkOutDate,
		PetCategory petCategory, String weight) {
		this.userId = userId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.petCategory = petCategory;
		this.weight = weight;
	}
}
