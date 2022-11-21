package com.encore.petandbe.controller.accommodation.accommodation.responses;

import lombok.Getter;

@Getter
public class AccommodationIdResponse {
	Long accommodationId;

	public AccommodationIdResponse(Long accommodationId) {
		this.accommodationId = accommodationId;
	}
}
