package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class ReviewListGetByAccommodationIdRequests {

	private Long roomId;
	private int pageNum;
	private int amount;

	public ReviewListGetByAccommodationIdRequests(Long roomId, int pageNum, int amount) {
		this.roomId = roomId;
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
