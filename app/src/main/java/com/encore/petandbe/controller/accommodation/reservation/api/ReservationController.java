package com.encore.petandbe.controller.accommodation.reservation.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.config.Permission;
import com.encore.petandbe.controller.accommodation.reservation.requests.DeleteReservationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.reservation.responses.DeleteReservationResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationDetailsResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationRetrieveResponse;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.reservation.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@Permission(role = Role.USER)
	@PostMapping
	public ResponseEntity<ReservationDetailsResponse> registerReservation(
		ReservationRegistrationRequest reservationRegistrationRequest, HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());

		ReservationRegistrationRequest reservationRegistration = new ReservationRegistrationRequest(
			Long.valueOf(userId),
			reservationRegistrationRequest.getRoomId(), reservationRegistrationRequest.getCheckInDate(),
			reservationRegistrationRequest.getCheckOutDate(), reservationRegistrationRequest.getAmount());

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(reservationService.registerReservation(reservationRegistration));
	}

	@GetMapping("/{reservation-id}")
	public ResponseEntity<ReservationRetrieveResponse> retrieveReservation(
		@PathVariable("reservation-id") Long reservationId) {
		return ResponseEntity.ok().body(reservationService.findByReservationId(reservationId));
	}

	@Permission(role = Role.USER)
	@PutMapping("/{reservation-id}")
	public ResponseEntity<ReservationDetailsResponse> updateReservation(
		@PathVariable("reservation-id") Long reservationId,
		@RequestBody ReservationUpdatingRequest reservationUpdatingRequest, HttpServletRequest httpServletRequest) {

		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());

		ReservationUpdatingRequest updateRequest = new ReservationUpdatingRequest(Long.valueOf(userId),
			reservationUpdatingRequest.getCheckInDate(),
			reservationUpdatingRequest.getCheckOutDate(), reservationUpdatingRequest.getAmount());

		return ResponseEntity.ok()
			.body(reservationService.updateReservation(reservationId, updateRequest));
	}

	@Permission(role = Role.USER)
	@DeleteMapping("/{reservation-id}")
	public ResponseEntity<DeleteReservationResponse> deleteReservation(
		@PathVariable("reservation-id") Long reservationId, HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());
		return ResponseEntity.ok()
			.body(reservationService.deleteReservation(
				new DeleteReservationRequest(Long.valueOf(userId), reservationId)));
	}
}
