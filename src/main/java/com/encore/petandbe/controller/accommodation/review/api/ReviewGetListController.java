package com.encore.petandbe.controller.accommodation.review.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.accommodation.review.requests.ReviewListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByUserIdResponse;
import com.encore.petandbe.service.accommodation.review.ReviewGetListService;

@RestController
@RequestMapping("/review-list")
public class ReviewGetListController {

	private final ReviewGetListService reviewGetListService;

	public ReviewGetListController(ReviewGetListService reviewGetListService) {
		this.reviewGetListService = reviewGetListService;
	}

	@GetMapping
	public ResponseEntity<ReviewListGetByUserIdResponse> getReviewListByUserId(
		@ModelAttribute ReviewListGetByUserIdRequests reviewListGetByUserIdRequests) {
		return ResponseEntity.ok().body(reviewGetListService.getReviewListByUserId(reviewListGetByUserIdRequests));
	}
}
