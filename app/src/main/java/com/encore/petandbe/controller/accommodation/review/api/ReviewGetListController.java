package com.encore.petandbe.controller.accommodation.review.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.accommodation.review.requests.ReviewListGetByAccommodationIdRequests;
import com.encore.petandbe.controller.accommodation.review.requests.ReviewListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByAccommodationIdResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByUserIdResponse;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.review.ReviewGetListService;

@RestController
@RequestMapping("/review-list")
public class ReviewGetListController {

	private final ReviewGetListService reviewGetListService;

	public ReviewGetListController(ReviewGetListService reviewGetListService) {
		this.reviewGetListService = reviewGetListService;
	}

	@GetMapping("/user")
	public ResponseEntity<ReviewListGetByUserIdResponse> getReviewListByUserId(
		@ModelAttribute ReviewListGetByUserIdRequests reviewListGetByUserIdRequests,
		HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());

		ReviewListGetByUserIdRequests reviewListGetByUserIdRequest = new ReviewListGetByUserIdRequests(
			Long.valueOf(userId),
			reviewListGetByUserIdRequests.getPageNum(), reviewListGetByUserIdRequests.getAmount());

		return ResponseEntity.ok().body(reviewGetListService.getReviewListByUserId(reviewListGetByUserIdRequest));
	}

	@GetMapping
	public ResponseEntity<ReviewListGetByAccommodationIdResponse> getReviewListByAccommodationId(
		@ModelAttribute ReviewListGetByAccommodationIdRequests reviewListGetByAccommodationIdRequests) {
		return ResponseEntity.ok()
			.body(reviewGetListService.getReviewListByAccommodationId(reviewListGetByAccommodationIdRequests));
	}
}
