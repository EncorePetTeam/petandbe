package com.encore.petandbe.controller.accommodation.reservation.responses;

import java.time.LocalDateTime;

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
	private Integer amount;

	public ReservationRetrieveResponse(Long reservationId, Long userId, Long roomId, Long accommodationId,
		String accommodationName, String roomName, LocalDateTime checkInDate, LocalDateTime checkOutDate,
		Integer amount) {
		this.reservationId = reservationId;
		this.userId = userId;
		this.roomId = roomId;
		this.accommodationId = accommodationId;
		this.accommodationName = accommodationName;
		this.roomName = roomName;
		this.checkInDate = LocalDateTimeValidator.of().convertLocalDateTimeToString(checkInDate);
		this.checkOutDate = LocalDateTimeValidator.of().convertLocalDateTimeToString(checkOutDate);
		this.amount = amount;
	}
}
