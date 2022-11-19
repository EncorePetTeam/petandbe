package com.encore.petandbe.controller.accommodation.review.responses;

import lombok.Getter;

@Getter
public class DeleteReviewResponse {

	private Long reviewId;
	private Boolean state;
	private Long reservationId;

	public DeleteReviewResponse(Long reviewId, Boolean state, Long reservationId) {
		this.reviewId = reviewId;
		this.state = state;
		this.reservationId = reservationId;
	}
}
