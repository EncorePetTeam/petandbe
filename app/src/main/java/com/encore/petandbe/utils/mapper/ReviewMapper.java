package com.encore.petandbe.utils.mapper;

import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.RegistReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.review.Review;
import com.encore.petandbe.model.user.user.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewMapper {

	private static ReviewMapper reviewMapper = null;

	public static ReviewMapper of() {
		if (reviewMapper == null) {
			reviewMapper = new ReviewMapper();
		}
		return reviewMapper;
	}

	public Review registerReviewRequestsToEntity(RegistReviewRequests registReviewRequests, User user,
		Reservation reservation) {
		return Review.builder().user(user)
			.reservation(reservation)
			.rate(registReviewRequests.getRate())
			.content(registReviewRequests.getContent())
			.build();
	}

	public RegistReviewResponse registerEntityToResponse(Review afterSaveReview) {
		return new RegistReviewResponse(afterSaveReview.getId(), afterSaveReview.getUser().getId(),
			afterSaveReview.getRate(), afterSaveReview.getContent(), afterSaveReview.getReservation().getId());
	}

	public ReviewDetailsResponse entityToResponse(Review review) {
		return new ReviewDetailsResponse(review.getId(), review.getReservation().getRoom().getRoomName(),
			review.getUser().getId(), review.getRate(),
			review.getContent(), review.getReservation().getId());
	}

	public DeleteReviewResponse deletedEntityToResponse(Review review) {
		return new DeleteReviewResponse(review.getId(), review.getState(), review.getReservation().getId());
	}
}
