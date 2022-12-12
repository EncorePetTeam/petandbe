package com.encore.petandbe.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationRetrieveResponse;
import com.encore.petandbe.model.accommodation.accommodation.QAccommodation;
import com.encore.petandbe.model.accommodation.reservation.QReservation;
import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.room.QRoom;
import com.querydsl.core.types.Projections;

@Repository
public class QuerydslReservationRetrieveRepository extends QuerydslRepositorySupport
	implements ReservationRetrieveRepository {

	private QReservation qReservation = QReservation.reservation;
	private QAccommodation qAccommodation = QAccommodation.accommodation;
	private QRoom qRoom = QRoom.room;

	public QuerydslReservationRetrieveRepository() {
		super(Reservation.class);
	}

	@Override
	public ReservationRetrieveResponse getReservationRetrieveResponseByReservationId(Long reservationId) {
		return from(qReservation)
			.select(Projections.constructor(ReservationRetrieveResponse.class, qReservation.id, qReservation.user.id,
				qRoom.id, qAccommodation.id, qAccommodation.accommodationName, qRoom.roomName, qReservation.checkInDate,
				qReservation.checkOutDate, qReservation.petCategory, qReservation.weight))
			.leftJoin(qRoom)
			.fetchJoin()
			.on(qRoom.id.eq(qReservation.room.id))
			.leftJoin(qAccommodation)
			.fetchJoin()
			.on(qAccommodation.id.eq(qRoom.accommodation.id))
			.where(qReservation.id.eq(reservationId)).fetchOne();
	}
}
