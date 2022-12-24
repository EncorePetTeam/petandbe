package com.encore.petandbe.service.accommodation.reservation.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ReservationUpdatingDTO {

	private final LocalDateTime checkInDate;
	private final LocalDateTime checkOutDate;
	private final Integer amount;

	public ReservationUpdatingDTO(LocalDateTime checkInDate, LocalDateTime checkOutDate, Integer amount) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.amount = amount;
	}
}
