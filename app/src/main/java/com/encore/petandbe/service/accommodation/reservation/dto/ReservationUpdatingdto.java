package com.encore.petandbe.service.accommodation.reservation.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ReservationUpdatingdto {

	private LocalDateTime checkInDate;
	private LocalDateTime checkOutDate;
	private Integer amount;

	public ReservationUpdatingdto(LocalDateTime checkInDate, LocalDateTime checkOutDate, Integer amount) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.amount = amount;
	}
}
