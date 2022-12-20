package com.encore.petandbe.controller.accommodation.review.responses;

import java.util.List;

import lombok.Getter;

@Getter
public class ReviewListGetByAccommodationIdResponse {

	private int endPage;
	private int selectPage;
	private int amountItem;
	private List<ReviewDetailsResponse> reviewDetailsResponseList;

	public ReviewListGetByAccommodationIdResponse(int endPage, int selectPage, int amountItem,
		List<ReviewDetailsResponse> reviewDetailsResponseList) {
		this.endPage = endPage;
		this.selectPage = selectPage;
		this.amountItem = amountItem;
		this.reviewDetailsResponseList = reviewDetailsResponseList;
	}
}
