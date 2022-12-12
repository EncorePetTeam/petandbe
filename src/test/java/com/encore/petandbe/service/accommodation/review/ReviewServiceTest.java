package com.encore.petandbe.service.accommodation.review;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
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

import com.encore.petandbe.controller.accommodation.review.requests.DeleteReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.UpdateReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.RegistReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.exception.WrongRequestException;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.review.Review;
import com.encore.petandbe.model.accommodation.room.Room;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.ReservationRepository;
import com.encore.petandbe.repository.ReviewListSearchRepository;
import com.encore.petandbe.repository.ReviewRepository;
import com.encore.petandbe.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

	@InjectMocks
	private ReviewService reviewService;

	@Mock
	private ReviewRepository reviewRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ReservationRepository reservationRepository;

	@Mock
	private ReviewListSearchRepository reviewListSearchRepository;

	@Test
	@DisplayName("Register Review service - success ")
	void registerReviewSuccess() {
		//given
		Long userId = 1L;
		Integer rate = 5;
		String content = "very good";
		Long reservationId = 1L;

		RegistReviewRequests registReviewRequests = new RegistReviewRequests(userId, rate, content, reservationId);

		User user = User.builder().id(userId).build();

		Accommodation accommodation = Accommodation.builder().id(1L).build();

		Reservation reservation = Reservation.builder()
			.id(reservationId)
			.user(user)
			.checkInDate(LocalDateTime.parse("2022-11-23T16:00:00"))
			.checkOutDate(LocalDateTime.parse("2022-11-24T17:00:00"))
			.state(false)
			.petCategory(
				PetCategory.DOG)
			.weight("5")
			.room(Room.builder().id(1L).accommodation(accommodation).build())
			.build();

		Review review = Review.builder()
			.user(User.builder().id(userId).build())
			.reservation(reservation)
			.rate(rate)
			.content(content)
			.state(false)
			.build();

		List<Reservation> reservationList = new ArrayList<>();

		reservationList.add(reservation);

		List<Integer> rateList = new ArrayList<>();
		rateList.add(rate);

		given(reservationRepository.findById(reservationId)).willReturn(Optional.ofNullable(reservation));
		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewRepository.save(any(Review.class))).willReturn(review);
		given(reviewListSearchRepository.getReviewRatesByAccommodationId(any(Accommodation.class))).willReturn(
			rateList);
		//when
		RegistReviewResponse registReviewResponse = reviewService.registReview(registReviewRequests);

		//then
		assertEquals(reservationId, registReviewResponse.getReservationId());
		assertEquals(userId, registReviewResponse.getUserId());
		assertEquals(rate, registReviewResponse.getRate());
		assertEquals(content, registReviewResponse.getContent());
	}

	@Test
	@DisplayName("Register Review service without content - success ")
	void registerReviewWithoutContentSuccess() {
		//given
		Long userId = 1L;
		Integer rate = 5;
		Long reservationId = 1L;

		RegistReviewRequests registReviewRequests = new RegistReviewRequests(userId, rate, null, reservationId);

		User user = User.builder().id(userId).build();

		Accommodation accommodation = Accommodation.builder().id(1L).build();

		Reservation reservation = Reservation.builder()
			.id(reservationId)
			.user(user)
			.checkInDate(LocalDateTime.parse("2022-11-17T11:00:00"))
			.checkOutDate(LocalDateTime.parse("2022-11-19T16:00:00"))
			.state(false)
			.petCategory(
				PetCategory.DOG)
			.weight("5")
			.room(Room.builder().id(1L).accommodation(accommodation).build())
			.build();

		Review review = Review.builder()
			.user(User.builder().id(userId).build())
			.reservation(reservation)
			.rate(rate)
			.state(false)
			.build();

		List<Reservation> reservationList = new ArrayList<>();
		reservationList.add(reservation);

		List<Integer> rateList = new ArrayList<>();
		rateList.add(rate);

		given(reservationRepository.findById(reservationId)).willReturn(Optional.ofNullable(reservation));
		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewRepository.save(any(Review.class))).willReturn(review);
		given(reviewListSearchRepository.getReviewRatesByAccommodationId(any(Accommodation.class))).willReturn(
			rateList);
		//when
		RegistReviewResponse registReviewResponse = reviewService.registReview(registReviewRequests);

		//then
		assertEquals(reservationId, registReviewResponse.getReservationId());
		assertEquals(userId, registReviewResponse.getUserId());
		assertEquals(rate, registReviewResponse.getRate());
		assertNull(registReviewResponse.getContent());
	}

	@Test
	@DisplayName("Register Review service - user not found fail")
	void registerReviewUserNotFoundFail() {
		//given
		Long userId = 1L;
		Integer rate = 5;
		String content = "very good";
		Long reservationId = 1L;

		RegistReviewRequests registReviewRequests = new RegistReviewRequests(userId, rate, content, reservationId);

		given(userRepository.findById(userId)).willReturn(Optional.empty());

		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			reviewService.registReview(registReviewRequests);
		});
	}

	@Test
	@DisplayName("Register Review service - reservation not found fail")
	void registerReviewReservationNotFoundFail() {
		//given
		Long userId = 1L;
		Integer rate = 5;
		String content = "very good";
		Long reservationId = 1L;

		RegistReviewRequests registReviewRequests = new RegistReviewRequests(userId, rate, content, reservationId);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(User.builder().id(userId).build()));
		given(reservationRepository.findById(reservationId)).willReturn(Optional.empty());

		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			reviewService.registReview(registReviewRequests);
		});
	}

	@Test
	@DisplayName("Register Review service - user does not match fail")
	void registerReviewUserDoesNotMatchFail() {
		//given
		Long userId = 1L;
		Integer rate = 5;
		String content = "very good";
		Long reservationId = 1L;

		RegistReviewRequests registReviewRequests = new RegistReviewRequests(userId, rate, content, reservationId);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(User.builder().id(userId).build()));
		given(reservationRepository.findById(reservationId)).willReturn(
			Optional.ofNullable(Reservation.builder().id(1L).user(User.builder().id(2L).build()).build()));

		//when
		Assertions.assertThrows(WrongRequestException.class, () -> {
			//then
			reviewService.registReview(registReviewRequests);
		});
	}

	@Test
	@DisplayName("Find review details service - success")
	void findReviewDetailsSuccess() {
		//given
		Long userId = 1L;
		Integer rate = 5;
		String content = "very good";
		Long reservationId = 1L;
		Long reviewId = 25L;

		Reservation reservation = Reservation.builder().id(reservationId).build();

		Review review = Review.builder()
			.id(reviewId)
			.reservation(reservation)
			.user(User.builder().id(userId).build())
			.rate(rate)
			.content(content)
			.state(false)
			.build();

		given(reservationRepository.findById(reservationId)).willReturn(Optional.ofNullable(reservation));
		given(reviewRepository.findByReservationId(reservation.getId())).willReturn(Optional.ofNullable(review));
		//when
		ReviewDetailsResponse reviewDetailsResponse = reviewService.findReviewDetails(reservationId);

		//then
		assertEquals(reviewId, reviewDetailsResponse.getReviewId());
		assertEquals(rate, reviewDetailsResponse.getRate());
		assertEquals(content, reviewDetailsResponse.getContent());
	}

	@Test
	@DisplayName("Find review details service - review does not exist fail")
	void findReviewDoesNotExistFail() {
		//given
		Long reservationId = 1L;

		Reservation reservation = Reservation.builder().id(reservationId).build();

		given(reservationRepository.findById(reservationId)).willReturn(Optional.ofNullable(reservation));
		assert reservation != null;
		given(reviewRepository.findByReservationId(reservation.getId())).willReturn(Optional.empty());
		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			reviewService.findReviewDetails(reservationId);
		});
	}

	@Test
	@DisplayName("Find review details service - reservation does not exist fail")
	void findReviewReservationDoesNotExistFail() {
		//given
		Long reservationId = 1L;

		given(reservationRepository.findById(anyLong())).willReturn(Optional.empty());
		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			reviewService.findReviewDetails(reservationId);
		});
	}

	@Test
	@DisplayName("Delete review service - success")
	void deleteReviewSuccess() {
		//given
		Long userId = 1L;
		Integer rate = 5;
		String content = "very good";
		Long reviewId = 25L;

		Accommodation accommodation = Accommodation.builder().id(23L).build();
		Room room = Room.builder().id(23L).accommodation(accommodation).build();
		Reservation reservation = Reservation.builder().id(27L).room(room).build();

		User user = User.builder()
			.id(userId)
			.build();

		Review review = Review.builder()
			.id(reviewId)
			.reservation(reservation)
			.user(user)
			.rate(rate)
			.content(content)
			.state(false)
			.build();

		DeleteReviewRequests deleteReviewRequests = new DeleteReviewRequests(reviewId, userId);

		List<Reservation> reservationList = new ArrayList<>();
		reservationList.add(reservation);

		List<Integer> rateList = new ArrayList<>();
		rateList.add(rate);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewRepository.findById(reviewId)).willReturn(Optional.ofNullable(review));
		given(reviewListSearchRepository.getReviewRatesByAccommodationId(any(Accommodation.class))).willReturn(
			rateList);
		//when
		DeleteReviewResponse deleteReviewResponse = reviewService.deleteReview(reviewId, userId);

		//then
		assertEquals(reviewId, deleteReviewResponse.getReviewId());
		assertEquals(true, deleteReviewResponse.getState());
	}

	@Test
	@DisplayName("Delete review service - user does not exist fail")
	void deleteReviewUserDoesNotExistFail() {
		//given
		Long userId = 1L;
		Long reviewId = 1L;

		DeleteReviewRequests deleteReviewRequests = new DeleteReviewRequests(userId, reviewId);

		given(userRepository.findById(userId)).willReturn(Optional.empty());
		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			reviewService.deleteReview(reviewId, userId);
		});
	}

	@Test
	@DisplayName("Delete review service - review does not exist fail")
	void deleteReviewDoesNotExistFail() {
		//given
		Long userId = 1L;
		Long reviewId = 1L;

		User user = User.builder()
			.id(userId)
			.build();

		DeleteReviewRequests deleteReviewRequests = new DeleteReviewRequests(userId, reviewId);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewRepository.findById(anyLong())).willReturn(Optional.empty());
		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			reviewService.deleteReview(reviewId, userId);
		});
	}

	@Test
	@DisplayName("Delete review service - user does not match fail")
	void deleteReviewUserDoesNotMatchFail() {
		//given
		Long userId = 1L;
		Long reviewId = 1L;

		User user = User.builder()
			.id(userId)
			.build();

		Review review = Review.builder()
			.id(reviewId)
			.user(User.builder().id(2L).build())
			.build();

		DeleteReviewRequests deleteReviewRequests = new DeleteReviewRequests(userId, reviewId);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewRepository.findById(anyLong())).willReturn(Optional.ofNullable(review));
		//when
		Assertions.assertThrows(WrongRequestException.class, () -> {
			//then
			reviewService.deleteReview(reviewId, userId);
		});
	}

	@Test
	@DisplayName("Update review service - success")
	void updateReviewSuccess() {
		//given
		Long userId = 1L;
		Integer rate = 5;
		String content = "very good";
		Long reviewId = 25L;

		Accommodation accommodation = Accommodation.builder().id(23L).build();
		Room room = Room.builder().id(23L).accommodation(accommodation).build();
		Reservation reservation = Reservation.builder().id(27L).room(room).build();

		Integer updateRate = 3;
		String updateContent = "not bad";

		User user = User.builder()
			.id(userId)
			.build();

		Review review = Review.builder()
			.id(reviewId)
			.user(user)
			.reservation(reservation)
			.rate(rate)
			.content(content)
			.state(false)
			.build();

		UpdateReviewRequests updateReviewRequests = new UpdateReviewRequests(userId, updateRate,
			updateContent);

		List<Reservation> reservationList = new ArrayList<>();
		reservationList.add(reservation);

		List<Integer> rateList = new ArrayList<>();
		rateList.add(rate);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewRepository.findById(reviewId)).willReturn(Optional.ofNullable(review));
		given(reviewListSearchRepository.getReviewRatesByAccommodationId(any(Accommodation.class))).willReturn(
			rateList);
		//when
		ReviewDetailsResponse reviewDetailsResponse = reviewService.updateReview(reviewId, updateReviewRequests);

		//then
		assertEquals(reviewId, reviewDetailsResponse.getReviewId());
		assertEquals(updateRate, reviewDetailsResponse.getRate());
		assertEquals(updateContent, reviewDetailsResponse.getContent());
	}

	@Test
	@DisplayName("Update review service - content null success")
	void updateReviewContentNullSuccess() {
		//given
		Long userId = 1L;
		Integer rate = 5;
		String content = "very good";
		Long reviewId = 25L;

		Accommodation accommodation = Accommodation.builder().id(23L).build();
		Room room = Room.builder().id(23L).accommodation(accommodation).build();
		Reservation reservation = Reservation.builder().id(27L).room(room).build();

		Integer updateRate = 3;

		User user = User.builder()
			.id(userId)
			.build();

		Review review = Review.builder()
			.id(reviewId)
			.user(user)
			.reservation(reservation)
			.rate(rate)
			.content(content)
			.state(false)
			.build();

		UpdateReviewRequests updateReviewRequests = new UpdateReviewRequests(userId, updateRate,
			null);

		List<Reservation> reservationList = new ArrayList<>();
		reservationList.add(reservation);

		List<Integer> rateList = new ArrayList<>();
		rateList.add(rate);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewRepository.findById(reviewId)).willReturn(Optional.ofNullable(review));
		given(reviewListSearchRepository.getReviewRatesByAccommodationId(any(Accommodation.class))).willReturn(
			rateList);
		//when
		ReviewDetailsResponse reviewDetailsResponse = reviewService.updateReview(reviewId, updateReviewRequests);

		//then
		assertEquals(reviewId, reviewDetailsResponse.getReviewId());
		assertEquals(updateRate, reviewDetailsResponse.getRate());
		assertNull(reviewDetailsResponse.getContent());
	}

	@Test
	@DisplayName("Update review service - user does not exist fail")
	void updateReviewUserDoesNotExistFail() {
		//given
		Long userId = 1L;
		Long reviewId = 1L;
		Integer rate = 5;
		String content = "";

		UpdateReviewRequests deleteReviewRequests = new UpdateReviewRequests(userId, rate, content);

		given(userRepository.findById(userId)).willReturn(Optional.empty());
		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			reviewService.updateReview(reviewId, deleteReviewRequests);
		});
	}

	@Test
	@DisplayName("Update review service - review does not exist fail")
	void updateReviewDoesNotExistFail() {
		//given
		Long userId = 1L;
		Long reviewId = 1L;
		Integer rate = 5;
		String content = "";

		User user = User.builder()
			.id(userId)
			.build();

		UpdateReviewRequests deleteReviewRequests = new UpdateReviewRequests(userId, rate, content);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewRepository.findById(reviewId)).willReturn(Optional.empty());
		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			reviewService.updateReview(reviewId, deleteReviewRequests);
		});
	}

	@Test
	@DisplayName("Update review service - user does not match fail")
	void updateReviewUserDoesNotMatchFail() {
		//given
		Long userId = 1L;
		Long reviewId = 1L;
		Integer rate = 5;
		String content = "";

		User user = User.builder()
			.id(userId)
			.build();

		Review review = Review.builder()
			.id(reviewId)
			.user(User.builder().id(2L).build())
			.build();

		UpdateReviewRequests deleteReviewRequests = new UpdateReviewRequests(userId, rate, content);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(reviewRepository.findById(reviewId)).willReturn(Optional.ofNullable(review));
		//when
		Assertions.assertThrows(WrongRequestException.class, () -> {
			//then
			reviewService.updateReview(reviewId, deleteReviewRequests);
		});
	}
}