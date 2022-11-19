package com.encore.petandbe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByReservationId(Reservation reservation);
}
