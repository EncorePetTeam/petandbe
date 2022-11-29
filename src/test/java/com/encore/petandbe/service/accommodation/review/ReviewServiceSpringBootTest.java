package com.encore.petandbe.service.accommodation.review;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.review.requests.DeleteReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.UpdateReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.RegistReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;

@SpringBootTest
@Transactional
class ReviewServiceSpringBootTest {

	@Autowired
	private ReviewService reviewService;

	@Test
	@DisplayName("register review real test - success")
	void registerReview() {
		//given
		Long userId = 2L;
		Integer rate = 5;
		String content = "very good";
		Long reservationId = 1L;

		RegistReviewRequests registReviewRequests = new RegistReviewRequests(userId, rate, content, reservationId);
		//when
		RegistReviewResponse registReviewResponse = reviewService.registReview(registReviewRequests);
		//then
		assertEquals(rate, registReviewResponse.getRate());
		assertEquals(userId, registReviewResponse.getUserId());
		assertEquals(reservationId, registReviewResponse.getReservationId());
	}

	@Test
	@DisplayName("find review detail real test - success")
	void findReviewDetail() {
		//given
		Long reservationId = 2L;

		Long reviewId = 1L;
		Integer rate = 5;
		String content = "very good";

		//when
		ReviewDetailsResponse reviewDetailsResponse = reviewService.findReviewDetails(reservationId);
		//then
		assertEquals(reviewId, reviewDetailsResponse.getReviewId());
		assertEquals(rate, reviewDetailsResponse.getRate());
		assertEquals(content, reviewDetailsResponse.getContent());
	}

	@Test
	@DisplayName("update review real test - success")
	void updateReview() {
		//given
		Long userId = 3L;
		Integer rate = 3;
		String content = "not bad";
		Long reviewId = 1L;
		Long reservationId = 2L;

		UpdateReviewRequests updateReviewRequests = new UpdateReviewRequests(userId, rate, content);
		//when
		ReviewDetailsResponse reviewDetailsResponse = reviewService.updateReview(reviewId, updateReviewRequests);
		//then
		assertEquals(reviewId, reviewDetailsResponse.getReviewId());
		assertEquals(rate, reviewDetailsResponse.getRate());
		assertEquals(content, reviewDetailsResponse.getContent());
		assertEquals(reservationId, reviewDetailsResponse.getReservationId());
		assertEquals(userId, reviewDetailsResponse.getUserId());
	}

	@Test
	@DisplayName("delete review real test - success")
	void deleteReview() {
		//given
		Long userId = 3L;
		Long reviewId = 1L;

		DeleteReviewRequests deleteReviewRequests = new DeleteReviewRequests(reviewId, userId);
		//when
		DeleteReviewResponse deleteReviewResponse = reviewService.deleteReview(reviewId, deleteReviewRequests);

		assertEquals(true, deleteReviewResponse.getState());
		assertEquals(reviewId, deleteReviewResponse.getReviewId());
	}
}
