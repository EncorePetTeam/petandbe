package com.encore.petandbe.controller.accommodation.reservation.responses;

import java.time.LocalDateTime;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
import com.encore.petandbe.utils.validator.LocalDateTimeValidator;

import lombok.Getter;

@Getter
public class ReservationRetrieveResponse {

	private Long reservationId;
	private Long userId;
	private Long roomId;
	private Long accommodationId;
	private String accommodationName;
	private String roomName;
	private String checkInDate;
	private String checkOutDate;
	private PetCategory petCategory;
	private String weight;

	public ReservationRetrieveResponse(Long reservationId, Long userId, Long roomId, Long accommodationId,
		String accommodationName, String roomName, LocalDateTime checkInDate, LocalDateTime checkOutDate,
		PetCategory petCategory,
		String weight) {
		this.reservationId = reservationId;
		this.userId = userId;
		this.roomId = roomId;
		this.accommodationId = accommodationId;
		this.accommodationName = accommodationName;
		this.roomName = roomName;
		this.checkInDate = LocalDateTimeValidator.of().convertLocalDateTimeToString(checkInDate);
		this.checkOutDate = LocalDateTimeValidator.of().convertLocalDateTimeToString(checkOutDate);
		this.petCategory = petCategory;
		this.weight = weight;
	}
}
