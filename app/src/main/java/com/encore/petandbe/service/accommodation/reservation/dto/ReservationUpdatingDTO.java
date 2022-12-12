package com.encore.petandbe.service.accommodation.reservation.dto;

import java.time.LocalDateTime;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;

import lombok.Getter;

@Getter
public class ReservationUpdatingDTO {

	private LocalDateTime checkInDate;
	private LocalDateTime checkOutDate;
	private PetCategory petCategory;
	private String weight;

	public ReservationUpdatingDTO(LocalDateTime checkInDate, LocalDateTime checkOutDate,
		PetCategory petCategory, String weight) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.petCategory = petCategory;
		this.weight = weight;
	}
}
