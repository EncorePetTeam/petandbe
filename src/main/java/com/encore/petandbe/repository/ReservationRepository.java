package com.encore.petandbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.petandbe.model.accommodation.reservation.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByAccommodationId(Long accommodationId);
}
