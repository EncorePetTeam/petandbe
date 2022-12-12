package com.encore.petandbe.controller.accommodation.review.responses;

import lombok.Getter;

@Getter
public class ReviewWithAccommodationResponse {

	private String accommodationName;
	private Long accommodationId;
	private Long reservationId;
	private Integer reviewRate;
	private String reviewContent;

	public ReviewWithAccommodationResponse(String accommodationName, Long accommodationId, Long reservationId,
		Integer reviewRate, String reviewContent) {
		this.accommodationName = accommodationName;
		this.accommodationId = accommodationId;
		this.reservationId = reservationId;
		this.reviewRate = reviewRate;
		this.reviewContent = reviewContent;
	}
}
