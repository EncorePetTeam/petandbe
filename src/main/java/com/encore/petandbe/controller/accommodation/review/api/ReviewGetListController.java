package com.encore.petandbe.controller.accommodation.review.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.accommodation.review.requests.GetReviewListByUserIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.GetReviewListByUserIdResponse;
import com.encore.petandbe.service.accommodation.review.ReviewGetListService;

@RestController
@RequestMapping("/review-list")
public class ReviewGetListController {

	private final ReviewGetListService reviewGetListService;

	public ReviewGetListController(ReviewGetListService reviewGetListService) {
		this.reviewGetListService = reviewGetListService;
	}

	@PostMapping("/get-by-userid")
	public ResponseEntity<GetReviewListByUserIdResponse> getReviewListByUserId(
		@RequestBody GetReviewListByUserIdRequests getReviewListByUserIdRequests) {
		return ResponseEntity.ok().body(reviewGetListService.getReviewListByUserId(getReviewListByUserIdRequests));
	}
}
