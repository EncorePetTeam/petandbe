package com.encore.petandbe.service.accommodation.review;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.review.requests.DeleteReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.UpdateReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.RegistReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.exception.WrongRequestException;
import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.review.Review;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.ReservationRepository;
import com.encore.petandbe.repository.ReviewRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.ReviewMapper;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;
	private UserRepository userRepository;
	private ReservationRepository reservationRepository;
	private ReviewMapper reviewMapper = new ReviewMapper();

	public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository,
		ReservationRepository reservationRepository) {
		this.reviewRepository = reviewRepository;
		this.userRepository = userRepository;
		this.reservationRepository = reservationRepository;
	}

	@Transactional
	public RegistReviewResponse registReview(RegistReviewRequests registReviewRequests) {
		User user = userRepository.findById(registReviewRequests.getUserId())
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));

		Reservation reservation = reservationRepository.findById(registReviewRequests.getReservationId())
			.orElseThrow(() -> new NonExistResourceException("Reservation could not be found"));

		Review afterSaveReview = reviewRepository.save(
			reviewMapper.registReviewRequestsToEntity(registReviewRequests, user));

		Reservation updatedReservation = reservationRepository.save(Reservation.builder()
			.id(reservation.getId())
			.review(afterSaveReview)
			.build());

		return reviewMapper.entityToResponse(afterSaveReview, updatedReservation.getId());
	}

	@Transactional
	public ReviewDetailsResponse findReviewDetails(Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId)
			.orElseThrow(() -> new NonExistResourceException("Reservation could not be found"));

		if (reservation.getReview() == null) {
			throw new NonExistResourceException("Review does not exist");
		}

		return reviewMapper.entityToResponse(reservation.getReview());
	}

	@Transactional
	public ReviewDetailsResponse updateReview(UpdateReviewRequests updateReviewRequests) {
		User user = userRepository.findById(updateReviewRequests.getUserId())
			.orElseThrow(() -> new NonExistResourceException("user does not exist"));

		Review review = reviewRepository.findById(updateReviewRequests.getReviewId())
			.orElseThrow(() -> new NonExistResourceException("Review does not exist"));

		if (!user.getId().equals(review.getUser().getId())) {
			throw new WrongRequestException("user inconsistency");
		}

		Review updatedReview = reviewRepository.save(reviewMapper.updateReviewRequestsToEntity(updateReviewRequests));

		return reviewMapper.entityToResponse(updatedReview);
	}

	@Transactional
	public DeleteReviewResponse deleteReview(DeleteReviewRequests deleteReviewRequests) {
		User user = userRepository.findById(deleteReviewRequests.getUserId())
			.orElseThrow(() -> new NonExistResourceException("user could not be found"));

		Review review = reviewRepository.findById(deleteReviewRequests.getReviewId())
			.orElseThrow(() -> new NonExistResourceException("review could not be found"));

		if (!user.getId().equals(review.getUser().getId())) {
			throw new WrongRequestException("user inconsistency");
		}

		Review deletedReview = reviewRepository.save(Review.builder().id(review.getId()).state(true).build());
		return reviewMapper.deletedEntityToResponse(deletedReview);
	}
}
