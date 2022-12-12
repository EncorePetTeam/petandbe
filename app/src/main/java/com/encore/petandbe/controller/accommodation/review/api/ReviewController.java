package com.encore.petandbe.controller.accommodation.review.api;

import javax.servlet.http.HttpServletRequest;

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

import com.encore.petandbe.config.Permission;
import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.UpdateReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.RegistReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.review.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@Permission(role = Role.USER)
	@PostMapping
	public ResponseEntity<RegistReviewResponse> registerReview(@RequestBody RegistReviewRequests registReviewRequests,
		HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());

		RegistReviewRequests registerReviewRequest = new RegistReviewRequests(Long.valueOf(userId), registReviewRequests.getRate(),
			registReviewRequests.getContent(), registReviewRequests.getReservationId());

		return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.registReview(registerReviewRequest));
	}

	@GetMapping("/{reservation-id}")
	public ResponseEntity<ReviewDetailsResponse> reviewDetails(@PathVariable("reservation-id") Long reservationId) {
		return ResponseEntity.ok().body(reviewService.findReviewDetails(reservationId));
	}

	@Permission(role = Role.USER)
	@PutMapping("/{review-id}")
	public ResponseEntity<ReviewDetailsResponse> updateReview(@PathVariable("review-id") Long reviewId,
		@RequestBody UpdateReviewRequests updateReviewRequests,
		HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());

		UpdateReviewRequests updateReviewRequest = new UpdateReviewRequests(Long.valueOf(userId), updateReviewRequests.getRate(),
			updateReviewRequests.getContent());

		return ResponseEntity.ok().body(reviewService.updateReview(reviewId, updateReviewRequest));
	}

	@Permission(role = Role.USER)
	@DeleteMapping("/{review-id}")
	public ResponseEntity<DeleteReviewResponse> deleteReview(@PathVariable("review-id") Long reviewId,
		HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());
		return ResponseEntity.ok().body(reviewService.deleteReview(reviewId, Long.valueOf(userId)));
	}
}
