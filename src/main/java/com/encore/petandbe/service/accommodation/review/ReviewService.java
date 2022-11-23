package com.encore.petandbe.service.accommodation.review;

import java.util.List;

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
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.review.Review;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.repository.ReservationRepository;
import com.encore.petandbe.repository.ReviewRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.ReviewMapper;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;
	private UserRepository userRepository;
	private ReservationRepository reservationRepository;
	private AccommodationRepository accommodationRepository;

	public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository,
		ReservationRepository reservationRepository, AccommodationRepository accommodationRepository) {
		this.reviewRepository = reviewRepository;
		this.userRepository = userRepository;
		this.reservationRepository = reservationRepository;
		this.accommodationRepository = accommodationRepository;
	}

	@Transactional
	public RegistReviewResponse registReview(RegistReviewRequests registReviewRequests) {
		User user = userRepository.findById(registReviewRequests.getUserId())
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));

		Reservation reservation = reservationRepository.findById(registReviewRequests.getReservationId())
			.orElseThrow(() -> new NonExistResourceException("Reservation could not be found"));

		checkUserIsMatch(user, reservation.getUser());

		if (!reviewRepository.findByReservationId(reservation.getId()).isEmpty()) {
			throw new WrongRequestException("Review already exist, please update review");
		}

		Review savedReview = reviewRepository.save(
			ReviewMapper.of().registReviewRequestsToEntity(registReviewRequests, user, reservation));

		updateAccommodationRate(reservation.getAccommodation());

		return ReviewMapper.of().registedEntityToResponse(savedReview);
	}

	@Transactional
	public ReviewDetailsResponse findReviewDetails(Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId)
			.orElseThrow(() -> new NonExistResourceException("Reservation could not be found"));

		Review review = reviewRepository.findByReservationId(reservation.getId())
			.orElseThrow(() -> new NonExistResourceException("Review could not be found"));

		return ReviewMapper.of().entityToResponse(review);
	}

	@Transactional
	public ReviewDetailsResponse updateReview(Long reviewId, UpdateReviewRequests updateReviewRequests) {
		User user = userRepository.findById(updateReviewRequests.getUserId())
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new NonExistResourceException("Review does not exist"));

		checkUserIsMatch(user, review.getUser());

		review.updateReview(updateReviewRequests);

		updateAccommodationRate(review.getReservation().getAccommodation());

		return ReviewMapper.of().entityToResponse(review);
	}

	@Transactional
	public DeleteReviewResponse deleteReview(Long reviewId, DeleteReviewRequests deleteReviewRequests) {
		User user = userRepository.findById(deleteReviewRequests.getUserId())
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));

		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> new NonExistResourceException("Review could not be found"));

		checkUserIsMatch(user, review.getUser());

		review.deleteReview();

		updateAccommodationRate(review.getReservation().getAccommodation());

		return ReviewMapper.of().deletedEntityToResponse(review);
	}

	private void checkUserIsMatch(User expect, User result) {
		if (!expect.equals(result)) {
			throw new WrongRequestException("user inconsistency");
		}
	}

	private void updateAccommodationRate(Accommodation accommodation) {
		List<Reservation> reservationList = reservationRepository.findByAccommodationId(accommodation.getId());

		List<Integer> reviewList = reviewRepository.findRateById(reservationList);

		double avgRate = 0;

		if (!reviewList.isEmpty()) {
			avgRate = reviewList.stream().mapToInt(Integer::intValue).average().getAsDouble();
		}

		accommodation.updateAvgRate(avgRate);
	}
}
