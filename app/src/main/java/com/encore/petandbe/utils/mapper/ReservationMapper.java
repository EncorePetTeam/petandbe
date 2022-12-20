package com.encore.petandbe.utils.mapper;

import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.reservation.responses.DeleteReservationResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationDetailsResponse;
import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.room.Room;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.service.accommodation.reservation.dto.ReservationUpdatingdto;
import com.encore.petandbe.utils.validator.LocalDateTimeValidator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationMapper {

	private static ReservationMapper reservationMapper = null;

	public static ReservationMapper of() {
		if (reservationMapper == null) {
			reservationMapper = new ReservationMapper();
		}
		return reservationMapper;
	}

	public Reservation convertRegistrationRequestToReservation(
		ReservationRegistrationRequest reservationRegistrationRequest, User user, Room room) {
		return Reservation.builder()
			.room(room)
			.user(user)
			.checkInDate(LocalDateTimeValidator.of()
				.convertStringToLocalDateTime(reservationRegistrationRequest.getCheckInDate()))
			.checkOutDate(LocalDateTimeValidator.of()
				.convertStringToLocalDateTime(reservationRegistrationRequest.getCheckOutDate()))
			.amount(reservationRegistrationRequest.getAmount())
			.build();
	}

	public ReservationDetailsResponse convertEntityToDetailsResponse(Reservation reservation) {
		return new ReservationDetailsResponse(reservation.getId(), reservation.getUser().getId(),
			reservation.getRoom().getId(),
			LocalDateTimeValidator.of().convertLocalDateTimeToString(reservation.getCheckInDate()),
			LocalDateTimeValidator.of().convertLocalDateTimeToString(reservation.getCheckOutDate()),
			reservation.getAmount());
	}

	public DeleteReservationResponse convertEntityToDeleteResponse(Reservation reservation) {
		return new DeleteReservationResponse(reservation.getId(), reservation.getState());
	}

	public ReservationUpdatingdto convertUpdateRequestToDto(ReservationUpdatingRequest reservationUpdatingRequest) {
		return new ReservationUpdatingdto(
			LocalDateTimeValidator.of().convertStringToLocalDateTime(reservationUpdatingRequest.getCheckInDate()),
			LocalDateTimeValidator.of()
				.convertStringToLocalDateTime(reservationUpdatingRequest.getCheckOutDate()),
			reservationUpdatingRequest.getAmount());
	}
}
