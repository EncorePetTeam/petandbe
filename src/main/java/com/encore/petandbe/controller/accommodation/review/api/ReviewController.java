package com.encore.petandbe.controller.accommodation.review.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.accommodation.review.requests.DeleteReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.UpdateReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.RegistReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.service.accommodation.review.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping
	public ResponseEntity<RegistReviewResponse> registReview(@RequestBody RegistReviewRequests registReviewRequests) {
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.registReview(registReviewRequests));
	}

	@GetMapping("/{reservation-id}")
	public ResponseEntity<ReviewDetailsResponse> reviewDetails(@PathVariable("reservation-id") Long reservationId) {
		return ResponseEntity.ok().body(reviewService.findReviewDetails(reservationId));
	}

	@PutMapping
	public ResponseEntity<ReviewDetailsResponse> updateReview(@RequestBody UpdateReviewRequests updateReviewRequests) {
		return ResponseEntity.ok().body(reviewService.updateReview(updateReviewRequests));
	}

	@DeleteMapping
	public ResponseEntity<DeleteReviewResponse> deleteReview(@RequestBody DeleteReviewRequests deleteReviewRequests) {
		return ResponseEntity.ok().body(reviewService.deleteReview(deleteReviewRequests));
	}
}
