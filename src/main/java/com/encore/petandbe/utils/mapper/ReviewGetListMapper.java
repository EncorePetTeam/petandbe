package com.encore.petandbe.utils.mapper;

import org.springframework.data.domain.Page;

import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByUserIdResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewWithAccommodationResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewGetListMapper {

	private static ReviewGetListMapper reviewGetListMapper = null;

	public static ReviewGetListMapper of() {
		if (reviewGetListMapper == null) {
			reviewGetListMapper = new ReviewGetListMapper();
		}
		return reviewGetListMapper;
	}

	public ReviewListGetByUserIdResponse reviewListToResponse(Page<ReviewWithAccommodationResponse> reviewPage) {
		return new ReviewListGetByUserIdResponse(reviewPage.getTotalPages(), reviewPage.getNumber() + 1,
			reviewPage.getNumberOfElements(), reviewPage.getContent());
	}
}
