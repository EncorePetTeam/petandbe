package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class ReviewListGetByUserIdRequests {

	private Long userId;
	private int pageNum;
	private int amount;

	public ReviewListGetByUserIdRequests(Long userId, int pageNum, int amount) {
		this.userId = userId;
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
