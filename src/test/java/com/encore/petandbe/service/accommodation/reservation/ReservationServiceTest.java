package com.encore.petandbe.service.accommodation.reservation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.reservation.requests.DeleteReservationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.reservation.responses.DeleteReservationResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationDetailsResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationRetrieveResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.exception.WrongRequestException;
import com.encore.petandbe.exception.WrongTimeException;
import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.repository.ReservationRepository;
import com.encore.petandbe.repository.RoomRepository;

@SpringBootTest
@Transactional
class ReservationServiceTest {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private RoomRepository roomRepository;

	private Long userId = 5L;
	private Long roomId = 1L;
	private String checkInDate = "2023-07-12 13:00:00";
	private String checkOutDate = "2023-07-13 14:00:00";
	private PetCategory petCategory = PetCategory.DOG;
	private String weight = "4.8";

	@Test
	@DisplayName("Register reservation - success")
	void registerReservationSuccess() {
		//given
		ReservationRegistrationRequest reservationRegistrationRequest = new ReservationRegistrationRequest(userId,
			roomId, checkInDate, checkOutDate, petCategory, weight);
		//when
		ReservationDetailsResponse reservationDetailsResponse = reservationService.registerReservation(
			reservationRegistrationRequest);
		Optional<Reservation> reservation = reservationRepository.findById(
			reservationDetailsResponse.getReservationId());
		//then
		assertNotNull(reservation);
	}

	@Test
	@DisplayName("Register reservation - wrong time fail")
	void registerReservationWrongTimeFail() {
		//given
		ReservationRegistrationRequest reservationRegistrationRequest = new ReservationRegistrationRequest(userId,
			roomId, "2022-07-11 13:00:00", "2022-12-11 13:55:00", petCategory, weight);
		//when //then
		assertThrows(WrongTimeException.class, () -> reservationService.registerReservation(
			reservationRegistrationRequest));
	}

	@Test
	@DisplayName("Register reservation - check in date is after then check out fail")
	void registerReservationCheckInDateIsAfterThenCheckOutFail() {
		//given
		ReservationRegistrationRequest reservationRegistrationRequest = new ReservationRegistrationRequest(userId,
			roomId, "2022-12-11 13:00:00", "2022-07-11 13:55:00", petCategory, weight);
		//when //then
		assertThrows(WrongTimeException.class, () -> reservationService.registerReservation(
			reservationRegistrationRequest));
	}

	@Test
	@DisplayName("Register reservation - wrong time format fail")
	void registerReservationWrongTimeFormatFail() {
		//given
		ReservationRegistrationRequest reservationRegistrationRequest = new ReservationRegistrationRequest(userId,
			roomId, "2022-1214", "2022-07:55:00", petCategory, weight);
		//when //then
		assertThrows(WrongTimeException.class, () -> reservationService.registerReservation(
			reservationRegistrationRequest));
	}

	@Test
	@DisplayName("Register reservation - not exist room fail")
	void registerReservationNotExistRoomFail() {
		//given
		Long roomId = roomRepository.count() + 1;

		ReservationRegistrationRequest reservationRegistrationRequest = new ReservationRegistrationRequest(userId,
			roomId, checkInDate, checkOutDate, petCategory, weight);
		//when //then
		assertThrows(NonExistResourceException.class, () -> reservationService.registerReservation(
			reservationRegistrationRequest));
	}

	@Test
	@DisplayName("Find reservation retrieve - success")
	void findByReservationIdSuccess() {
		//given
		Long reservationId = reservationRepository.count();
		//when
		ReservationRetrieveResponse reservationRetrieveResponse = reservationService.findByReservationId(reservationId);
		//then
		assertNotNull(reservationRetrieveResponse);
	}

	@Test
	@DisplayName("Find reservation retrieve - reservation not exist fail")
	void findByReservationIdFail() {
		//given
		Long reservationId = reservationRepository.count() + 1;
		//when //then
		assertThrows(NonExistResourceException.class, () -> reservationService.findByReservationId(
			reservationId));
	}

	@Test
	@DisplayName("Update reservation - success")
	void updateReservationSuccess() {
		//given
		Long reservationId = 11L;
		ReservationUpdatingRequest reservationUpdatingRequest = new ReservationUpdatingRequest(userId, checkInDate,
			checkOutDate, petCategory, weight);
		//when
		ReservationDetailsResponse reservationDetailsResponse = reservationService.updateReservation(reservationId,
			reservationUpdatingRequest);
		//then
		assertNotNull(reservationDetailsResponse);
	}

	@Test
	@DisplayName("Update reservation - already passed reservation fail")
	void updateReservationAlreadyPassedReservationFail() {
		//given
		Long reservationId = 4L;
		ReservationUpdatingRequest reservationUpdatingRequest = new ReservationUpdatingRequest(userId, checkInDate,
			checkOutDate, petCategory, weight);
		//when //then
		assertThrows(WrongRequestException.class,
			() -> reservationService.updateReservation(reservationId, reservationUpdatingRequest));
	}

	@Test
	@DisplayName("Update reservation - User does not match fail")
	void updateReservationUserDoesNotMatchFail() {
		//given
		Long reservationId = 3L;
		ReservationUpdatingRequest reservationUpdatingRequest = new ReservationUpdatingRequest(userId, checkInDate,
			checkOutDate, petCategory, weight);
		//when //then
		assertThrows(WrongRequestException.class,
			() -> reservationService.updateReservation(reservationId, reservationUpdatingRequest));
	}

	@Test
	@DisplayName("Update reservation - reservation not exist fail")
	void updateReservationNotExistFail() {
		//given
		Long reservationId = reservationRepository.count() + 1;
		ReservationUpdatingRequest reservationUpdatingRequest = new ReservationUpdatingRequest(userId, checkInDate,
			checkOutDate, petCategory, weight);
		//when //then
		assertThrows(NonExistResourceException.class,
			() -> reservationService.updateReservation(reservationId, reservationUpdatingRequest));
	}

	@Test
	@DisplayName("Delete reservation - success")
	void deleteReservationSuccess() {
		//given
		Long reservationId = 11L;
		DeleteReservationRequest deleteReservationRequest = new DeleteReservationRequest(userId, reservationId);
		//when
		DeleteReservationResponse deleteReservationResponse = reservationService.deleteReservation(
			deleteReservationRequest);
		//then
		assertNotNull(deleteReservationResponse);
		assertTrue(deleteReservationResponse.isState());
	}

	@Test
	@DisplayName("Delete reservation - user does not match fail")
	void deleteReservationUserDoesNotMatchFail() {
		//given
		Long reservationId = 3L;
		DeleteReservationRequest deleteReservationRequest = new DeleteReservationRequest(userId, reservationId);
		//when //then
		assertThrows(WrongRequestException.class,
			() -> reservationService.deleteReservation(deleteReservationRequest));
	}

	@Test
	@DisplayName("Delete reservation - reservation not exist fail")
	void deleteReservationNotExistFail() {
		//given
		Long reservationId = reservationRepository.count() + 1;
		DeleteReservationRequest deleteReservationRequest = new DeleteReservationRequest(userId, reservationId);
		//when //then
		assertThrows(NonExistResourceException.class,
			() -> reservationService.deleteReservation(deleteReservationRequest));
	}
}