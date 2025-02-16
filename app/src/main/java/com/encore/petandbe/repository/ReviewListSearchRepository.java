package com.encore.petandbe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewWithAccommodationResponse;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;

public interface ReviewListSearchRepository {
	Page<ReviewWithAccommodationResponse> getReviewListPageByUserId(Long userId, Pageable pageable);

	List<Integer> getReviewRatesByAccommodationId(Accommodation accommodation);

	Page<ReviewDetailsResponse> getReviewListPageByAccommodationId(Long accommodationId, Long roomId,
		Pageable pageable);
}
