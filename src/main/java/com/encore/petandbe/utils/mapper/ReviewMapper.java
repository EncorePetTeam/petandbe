package com.encore.petandbe.utils.mapper;

import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.UpdateReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.RegistReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.model.accommodation.review.Review;
import com.encore.petandbe.model.user.user.User;

public class ReviewMapper {

	public Review registReviewRequestsToEntity(RegistReviewRequests registReviewRequests, User user) {
		return Review.builder().user(user)
			.rate(registReviewRequests.getRate())
			.content(registReviewRequests.getContent())
			.build();
	}

	public RegistReviewResponse entityToResponse(Review afterSaveReview, Long revervationId) {
		return new RegistReviewResponse(afterSaveReview.getId(), afterSaveReview.getUser().getId(),
			afterSaveReview.getRate(), afterSaveReview.getContent(), revervationId);
	}

	public ReviewDetailsResponse entityToResponse(Review review) {
		return new ReviewDetailsResponse(review.getId(), review.getUser().getId(), review.getRate(),
			review.getContent());
	}

	public DeleteReviewResponse deletedEntityToResponse(Review review) {
		return new DeleteReviewResponse(review.getId(), review.getState());
	}

	public Review updateReviewRequestsToEntity(UpdateReviewRequests updateReviewRequests) {
		return Review.builder()
			.id(updateReviewRequests.getReviewId())
			.content(updateReviewRequests.getContent())
			.rate(updateReviewRequests.getRate())
			.build();
	}
}
