package com.encore.petandbe.service.accommodation.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.review.requests.ReviewListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByUserIdResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewWithAccommodationResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.ReviewListSearchRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.ReviewGetListMapper;

@Service
public class ReviewGetListService {

	private final ReviewListSearchRepository reviewListSearchRepository;
	private final UserRepository userRepository;

	public ReviewGetListService(ReviewListSearchRepository reviewListSearchRepository, UserRepository userRepository) {
		this.reviewListSearchRepository = reviewListSearchRepository;
		this.userRepository = userRepository;
	}

	public ReviewListGetByUserIdResponse getReviewListByUserId(
		ReviewListGetByUserIdRequests reviewListGetByUserIdRequests) {
		User user = userRepository.findById(reviewListGetByUserIdRequests.getUserId())
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));

		Page<ReviewWithAccommodationResponse> reviewPage = reviewListSearchRepository.getReviewListPage(user.getId(),
			convertPageRequest(reviewListGetByUserIdRequests));

		return ReviewGetListMapper.of().reviewListToResponse(reviewPage);
	}

	private static PageRequest convertPageRequest(ReviewListGetByUserIdRequests reviewListGetByUserIdRequests) {
		return PageRequest.of(reviewListGetByUserIdRequests.getPageNum() - 1,
			reviewListGetByUserIdRequests.getAmount());
	}
}
