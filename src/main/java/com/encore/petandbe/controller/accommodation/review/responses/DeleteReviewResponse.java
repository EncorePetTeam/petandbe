package com.encore.petandbe.controller.accommodation.review.responses;

import lombok.Getter;

@Getter
public class DeleteReviewResponse {

	private Long reviewId;
	private Boolean state;

	public DeleteReviewResponse(Long reviewId, Boolean state) {
		this.reviewId = reviewId;
		this.state = state;
	}
}
