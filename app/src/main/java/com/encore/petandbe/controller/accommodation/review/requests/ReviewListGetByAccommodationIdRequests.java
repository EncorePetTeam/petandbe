package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class ReviewListGetByAccommodationIdRequests {

	private Long accommodationId;
	private Long roomId;
	private int pageNum;
	private int amount;

	public ReviewListGetByAccommodationIdRequests(Long accommodationId, Long roomId, int pageNum, int amount) {
		this.accommodationId = accommodationId;
		this.roomId = roomId;
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
