package com.encore.petandbe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.encore.petandbe.controller.accommodation.review.responses.ReviewWithAccommodationResponse;

public interface ReviewListSearchRepository {
	Page<ReviewWithAccommodationResponse> getReviewListPage(Long userId, Pageable pageable);
}
