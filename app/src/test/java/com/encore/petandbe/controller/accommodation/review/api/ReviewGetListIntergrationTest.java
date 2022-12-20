package com.encore.petandbe.controller.accommodation.review.api;

import static org.hibernate.validator.internal.util.Contracts.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.review.requests.ReviewListGetByAccommodationIdRequests;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewListGetByAccommodationIdResponse;

@SpringBootTest
@Transactional
public class ReviewGetListIntergrationTest {

	@Autowired
	private ReviewGetListController reviewGetListController;

	private int pageNum = 1;
	private int amount = 10;
	private Long roomId = 1L;
	private Long accommodationId = 1L;

	@Test
	@DisplayName("Get Review List By Accommodation Id - success")
	void getReviewListByAccommodationId() throws Exception {
		//given
		ReviewListGetByAccommodationIdRequests reviewListGetByAccommodationIdRequests = new ReviewListGetByAccommodationIdRequests(
			null, pageNum, amount);
		//when
		ResponseEntity<ReviewListGetByAccommodationIdResponse> response = reviewGetListController.getReviewListByAccommodationId(
			accommodationId, reviewListGetByAccommodationIdRequests);
		//then
		assertNotNull(response.getBody().getReviewDetailsResponseList());
	}

	@Test
	@DisplayName("Get Review List By Accommodation Id With Room Id - success")
	void getReviewListByAccommodationIdWithRoomId() throws Exception {
		//given
		ReviewListGetByAccommodationIdRequests reviewListGetByAccommodationIdRequests = new ReviewListGetByAccommodationIdRequests(
			roomId, pageNum, amount);
		//when
		ResponseEntity<ReviewListGetByAccommodationIdResponse> response = reviewGetListController.getReviewListByAccommodationId(
			accommodationId, reviewListGetByAccommodationIdRequests);
		//then
		assertNotNull(response.getBody().getReviewDetailsResponseList());
	}
}
