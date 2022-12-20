package com.encore.petandbe.service.accommodation.reservation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.reservation.requests.DeleteReservationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.reservation.responses.DeleteReservationResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationDetailsResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationRetrieveResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.exception.WrongRequestException;
import com.encore.petandbe.model.accommodation.reservation.Reservation;
import com.encore.petandbe.model.accommodation.room.Room;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.ReservationRepository;
import com.encore.petandbe.repository.ReservationRetrieveRepository;
import com.encore.petandbe.repository.RoomRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.ReservationMapper;
import com.encore.petandbe.utils.validator.LocalDateTimeValidator;

@Service
public class ReservationService {

	private final UserRepository userRepository;
	private final ReservationRepository reservationRepository;
	private final RoomRepository roomRepository;
	private final ReservationRetrieveRepository reservationRetrieveRepository;

	public ReservationService(UserRepository userRepository, ReservationRepository reservationRepository,
		RoomRepository roomRepository, ReservationRetrieveRepository reservationRetrieveRepository) {
		this.userRepository = userRepository;
		this.reservationRepository = reservationRepository;
		this.roomRepository = roomRepository;
		this.reservationRetrieveRepository = reservationRetrieveRepository;
	}

	@Transactional
	public ReservationDetailsResponse registerReservation(
		ReservationRegistrationRequest reservationRegistrationRequest) {
		User user = checkUserExistAndGetUser(reservationRegistrationRequest.getUserId());

		Room room = roomRepository.findById(reservationRegistrationRequest.getRoomId())
			.orElseThrow(() -> new NonExistResourceException("Room could not be found"));

		LocalDateTimeValidator.of()
			.checkCheckInDateIsBeforeCheckOutDateTime(reservationRegistrationRequest.getCheckInDate(),
				reservationRegistrationRequest.getCheckOutDate());

		Reservation reservation = ReservationMapper.of()
			.convertRegistrationRequestToReservation(reservationRegistrationRequest, user, room);

		Reservation newReservation = reservationRepository.save(reservation);

		return ReservationMapper.of().convertEntityToDetailsResponse(newReservation);
	}

	public ReservationRetrieveResponse findByReservationId(Long reservationId) {
		Reservation reservation = checkReservationAndGetReservation(reservationId);

		return reservationRetrieveRepository.getReservationRetrieveResponseByReservationId(reservation.getId());
	}

	@Transactional
	public ReservationDetailsResponse updateReservation(Long reservationId,
		ReservationUpdatingRequest reservationUpdatingRequest) {
		User user = checkUserExistAndGetUser(reservationUpdatingRequest.getUserId());

		Reservation reservation = checkReservationAndGetReservation(reservationId);

		checkUserIsMatch(user, reservation.getUser());

		if (LocalDateTimeValidator.of().isReservationAlreadyPassed(reservation)) {
			throw new WrongRequestException("Reservation already passed");
		}

		LocalDateTimeValidator.of()
			.checkCheckInDateIsBeforeCheckOutDateTime(reservationUpdatingRequest.getCheckInDate(),
				reservationUpdatingRequest.getCheckOutDate());

		reservation.updateReservation(ReservationMapper.of().convertUpdateRequestToDto(reservationUpdatingRequest));

		return ReservationMapper.of().convertEntityToDetailsResponse(reservation);
	}

	@Transactional
	public DeleteReservationResponse deleteReservation(DeleteReservationRequest deleteReservationRequest) {
		User user = checkUserExistAndGetUser(deleteReservationRequest.getUserId());

		Reservation reservation = checkReservationAndGetReservation(deleteReservationRequest.getReservationId());

		checkUserIsMatch(user, reservation.getUser());

		reservation.deleteReservation();

		return ReservationMapper.of().convertEntityToDeleteResponse(reservation);
	}

	private void checkUserIsMatch(User expect, User result) {
		if (!expect.equals(result)) {
			throw new WrongRequestException("user inconsistency");
		}
	}

	private User checkUserExistAndGetUser(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));
	}

	private Reservation checkReservationAndGetReservation(Long reservationId) {
		return reservationRepository.findById(reservationId)
			.orElseThrow(() -> new NonExistResourceException("Reservation could not be found"));
	}
}
