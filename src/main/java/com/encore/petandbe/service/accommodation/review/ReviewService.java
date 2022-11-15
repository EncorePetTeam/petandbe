package com.encore.petandbe.service.accommodation.review;

import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.review.requests.DeleteReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.UpdateReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;

@Service
public class ReviewService {
	public ReviewDetailsResponse registReview(RegistReviewRequests registReviewRequests) {
		return null;
	}

	public ReviewDetailsResponse findReviewDetails(String reservationId) {
		return null;
	}

	public ReviewDetailsResponse updateReview(UpdateReviewRequests updateReviewRequests) {
		return null;
	}

	public DeleteReviewResponse deleteReview(DeleteReviewRequests deleteReviewRequests) {
		return null;
	}
}
