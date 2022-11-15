package com.encore.petandbe.controller.accommodation.review.responses;

import lombok.Getter;

@Getter
public class DeleteReviewResponse {

	private Long reviewId;
	private String state;

	public DeleteReviewResponse(Long reviewId, String state) {
		this.reviewId = reviewId;
		this.state = state;
	}
}
