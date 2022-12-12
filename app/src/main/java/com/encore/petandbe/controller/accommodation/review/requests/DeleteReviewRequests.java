package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class DeleteReviewRequests {

	private Long reviewId;
	private Long userId;

	public DeleteReviewRequests(Long reviewId, Long userId) {
		this.reviewId = reviewId;
		this.userId = userId;
	}
}
