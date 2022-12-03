package com.encore.petandbe.service.accommodation.reservation;

import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.reservation.requests.DeleteReservationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.reservation.responses.DeleteReservationResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationDetailsResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationRetrieveResponse;

@Service
public class ReservationService {
	public ReservationDetailsResponse registerReservation(
		ReservationRegistrationRequest reservationRegistrationRequest) {
		return null;
	}

	public ReservationRetrieveResponse findbyReservationId(Long reservationId) {
		return null;
	}

	public ReservationDetailsResponse updateReservation(Long reservationId,
		ReservationUpdatingRequest reservationUpdatingRequest) {
		return null;
	}

	public DeleteReservationResponse deleteReservation(DeleteReservationRequest deleteReservationRequest) {
		return null;
	}
}
