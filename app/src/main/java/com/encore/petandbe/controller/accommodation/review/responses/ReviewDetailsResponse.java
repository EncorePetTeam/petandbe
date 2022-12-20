package com.encore.petandbe.controller.accommodation.review.responses;

import lombok.Getter;

@Getter
public class ReviewDetailsResponse {

	private Long reviewId;
	private String roomName;
	private Long userId;
	private Integer rate;
	private String content;
	private Long reservationId;

	public ReviewDetailsResponse(Long reviewId, String roomName, Long userId, Integer rate, String content,
		Long reservationId) {
		this.reviewId = reviewId;
		this.roomName = roomName;
		this.userId = userId;
		this.rate = rate;
		this.content = content;
		this.reservationId = reservationId;
	}
}
