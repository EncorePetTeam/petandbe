package com.encore.petandbe.repository;

import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationRetrieveResponse;

public interface ReservationRetrieveRepository {

	public ReservationRetrieveResponse getReservationRetrieveResponseByReservationId(Long reservationId);
}
