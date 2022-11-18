package com.encore.petandbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.petandbe.model.accommodation.review.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
