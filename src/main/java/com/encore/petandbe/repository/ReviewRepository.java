package com.encore.petandbe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByReservationId(Reservation reservation);

	@Query("SELECT r.rate FROM review r WHERE r.reservation_id IN (:reservationList) and r.state = false")
	List<Integer> findRateById(List<Reservation> reservationList);
}
