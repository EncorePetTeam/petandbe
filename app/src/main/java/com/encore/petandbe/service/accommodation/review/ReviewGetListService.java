package com.encore.petandbe.service.accommodation.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.review.requests.ReviewListGetByAccommodationIdRequests;
import com.encore.petandbe.controller.accommodation.review.requests.ReviewListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByAccommodationIdResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByUserIdResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewWithAccommodationResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.repository.ReviewListSearchRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.ReviewGetListMapper;

@Service
public class ReviewGetListService {

	private final ReviewListSearchRepository reviewListSearchRepository;
	private final AccommodationRepository accommodationRepository;
	private final UserRepository userRepository;

	public ReviewGetListService(ReviewListSearchRepository reviewListSearchRepository,
		AccommodationRepository accommodationRepository, UserRepository userRepository) {
		this.reviewListSearchRepository = reviewListSearchRepository;
		this.accommodationRepository = accommodationRepository;
		this.userRepository = userRepository;
	}

	public ReviewListGetByUserIdResponse getReviewListByUserId(
		ReviewListGetByUserIdRequests reviewListGetByUserIdRequests) {
		User user = userRepository.findById(reviewListGetByUserIdRequests.getUserId())
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));

		Page<ReviewWithAccommodationResponse> reviewPage = reviewListSearchRepository.getReviewListPageByUserId(
			user.getId(),
			convertPageRequest(reviewListGetByUserIdRequests.getPageNum(), reviewListGetByUserIdRequests.getAmount()));

		return ReviewGetListMapper.of().reviewListToResponse(reviewPage);
	}

	private static PageRequest convertPageRequest(int pageNum, int amount) {
		return PageRequest.of(pageNum - 1, amount);
	}

	public ReviewListGetByAccommodationIdResponse getReviewListByAccommodationId(
		ReviewListGetByAccommodationIdRequests reviewListGetByAccommodationIdRequests) {
		Page<ReviewDetailsResponse> reviewDetailsResponses = reviewListSearchRepository.getReviewListPageByAccommodationId(
			reviewListGetByAccommodationIdRequests.getAccommodationId(),
			reviewListGetByAccommodationIdRequests.getRoomId(), convertPageRequest(
				reviewListGetByAccommodationIdRequests.getPageNum(),
				reviewListGetByAccommodationIdRequests.getAmount()));

		return ReviewGetListMapper.of().reviewListToDetailsResponse(reviewDetailsResponses);
	}
}
