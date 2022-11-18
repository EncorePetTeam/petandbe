package com.encore.petandbe.controller.accommodation.review.responses;

import lombok.Getter;

@Getter
public class RegistReviewResponse {

	private Long reviewId;
	private Long userId;
	private Integer rate;
	private String content;
	private Long reservationId;

	public RegistReviewResponse(Long reviewId, Long userId, Integer rate, String content, Long reservationId) {
		this.reviewId = reviewId;
		this.userId = userId;
		this.rate = rate;
		this.content = content;
		this.reservationId = reservationId;
	}
}
