package com.encore.petandbe.controller.accommodation.review.responses;

import java.util.List;

import lombok.Getter;

@Getter
public class ReviewListGetByUserIdResponse {
	private int endPage;
	private int selectPage;
	private int amountItem;
	private List<ReviewWithAccommodationResponse> reviewWithAccommodationResponsesList;

	public ReviewListGetByUserIdResponse(int endPage, int selectPage, int amountItem,
		List<ReviewWithAccommodationResponse> reviewWithAccommodationResponsesList) {
		this.endPage = endPage;
		this.selectPage = selectPage;
		this.amountItem = amountItem;
		this.reviewWithAccommodationResponsesList = reviewWithAccommodationResponsesList;
	}
}
