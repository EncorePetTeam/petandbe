package com.encore.petandbe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationWithRoomResponse;

public interface ReservationListSearchRepository {

	Page<ReservationWithRoomResponse> getReservationListPage(Long userId, Pageable pageable);

}
