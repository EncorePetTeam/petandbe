package com.encore.petandbe.service.accommodation.review;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.encore.petandbe.controller.accommodation.review.requests.ReviewListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByUserIdResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewWithAccommodationResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.ReviewListSearchRepository;
import com.encore.petandbe.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class ReviewGetListServiceTest {

	@InjectMocks
	private ReviewGetListService reviewGetListService;

	@Mock
	private ReviewListSearchRepository reviewListSearchRepository;

	@Mock
	private UserRepository userRepository;

	@Test
	@DisplayName("Get review list page - success")
	void getReviewListByUserIdSuccess() {
		//given
		Long userId = 5L;
		int pageNum = 1;
		int amount = 10;

		User user = User.builder().id(userId).build();

		String accommodationName = "정정일 애견 호텔";
		String reviewContent = "Very good ";

		List<ReviewWithAccommodationResponse> reviewWithAccommodationResponsesList = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			reviewWithAccommodationResponsesList.add(new ReviewWithAccommodationResponse(accommodationName + i, (long)i,
				(long)i, 5, reviewContent + i));
		}

		ReviewListGetByUserIdRequests reviewListGetByUserIdRequests = new ReviewListGetByUserIdRequests(userId, pageNum,
			amount);

		Page<ReviewWithAccommodationResponse> reviewWithAccommodationResponses = new PageImpl<>(
			reviewWithAccommodationResponsesList,
			PageRequest.of(pageNum - 1, amount), reviewWithAccommodationResponsesList.size());

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewListSearchRepository.getReviewListPageByUserId(anyLong(), any(Pageable.class))).willReturn(
			reviewWithAccommodationResponses);

		//when
		ReviewListGetByUserIdResponse reviewListByUserId = reviewGetListService.getReviewListByUserId(
			reviewListGetByUserIdRequests);

		assertEquals(pageNum, reviewListByUserId.getSelectPage());
		assertEquals(amount, reviewListByUserId.getReviewWithAccommodationResponsesList().size());
		assert (!reviewListByUserId.getReviewWithAccommodationResponsesList().isEmpty());
	}

	@Test
	@DisplayName("Get review list page - review does not exist success")
	void getReviewListByUserIdReviewDoesNotExistSuccess() {
		//given
		Long userId = 5L;
		int pageNum = 1;
		int amount = 10;

		User user = User.builder().id(userId).build();

		List<ReviewWithAccommodationResponse> reviewWithAccommodationResponsesList = new ArrayList<>();

		ReviewListGetByUserIdRequests reviewListGetByUserIdRequests = new ReviewListGetByUserIdRequests(userId, pageNum,
			amount);

		Page<ReviewWithAccommodationResponse> reviewWithAccommodationResponses = new PageImpl<>(
			reviewWithAccommodationResponsesList,
			PageRequest.of(pageNum - 1, amount), reviewWithAccommodationResponsesList.size());

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewListSearchRepository.getReviewListPageByUserId(anyLong(), any(Pageable.class))).willReturn(
			reviewWithAccommodationResponses);

		//when
		ReviewListGetByUserIdResponse reviewListByUserId = reviewGetListService.getReviewListByUserId(
			reviewListGetByUserIdRequests);

		assertEquals(pageNum, reviewListByUserId.getSelectPage());
		assert (reviewListByUserId.getReviewWithAccommodationResponsesList().isEmpty());
	}

	@Test
	@DisplayName("Get review list page  - user not found fail")
	void getReviewListByUserIdFail() {
		//given
		Long userId = 5L;
		int pageNum = 1;
		int amount = 10;

		ReviewListGetByUserIdRequests reviewListGetByUserIdRequests = new ReviewListGetByUserIdRequests(userId, pageNum,
			amount);

		given(userRepository.findById(userId)).willReturn(Optional.empty());

		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			reviewGetListService.getReviewListByUserId(reviewListGetByUserIdRequests);
		});
	}
}