package com.encore.petandbe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationWithRoomResponse;
import com.encore.petandbe.model.accommodation.accommodation.QAccommodation;
import com.encore.petandbe.model.accommodation.reservation.QReservation;
import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.room.QRoom;
import com.querydsl.core.types.Projections;

@Repository
public class QuerydslReservationSearchListRepository extends QuerydslRepositorySupport
	implements ReservationListSearchRepository {

	private final QReservation qReservation = QReservation.reservation;
	private final QAccommodation qAccommodation = QAccommodation.accommodation;
	private final QRoom qRoom = QRoom.room;

	public QuerydslReservationSearchListRepository() { super(Reservation.class);}

	@Override
	public Page<ReservationWithRoomResponse> getReservationListPage(Long userId, Pageable pageable) {
		List<ReservationWithRoomResponse> reservationWithRoomResponseList
			= getReservationWithRoomResponseList(userId, pageable);

		Long reservationAmount = countReservationAmountByUser(userId);

		return new PageImpl<>(reservationWithRoomResponseList, pageable, reservationAmount);
	}

	private Long countReservationAmountByUser(Long userId){
		return from(qReservation)
			.select(qReservation.count())
			.where(qReservation.user.id.eq(userId))
			.fetchOne();
	}

	private List<ReservationWithRoomResponse> getReservationWithRoomResponseList(Long userId,
		Pageable pageable){
		return from(qReservation)
			.select(Projections.constructor(ReservationWithRoomResponse.class,
				qAccommodation.id,
				qRoom.id,
				qReservation.checkInDate, qReservation.checkOutDate))
			.leftJoin(qRoom)
			.fetchJoin()
			.on(qRoom.id.eq(qReservation.room.id))
			.leftJoin(qAccommodation)
			.fetchJoin()
			.on(qAccommodation.id.eq(qRoom.accommodation.id))
			.where(qReservation.user.id.eq(userId))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(qReservation.id.desc()).fetch();
	}

}
