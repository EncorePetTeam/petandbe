package com.encore.petandbe.controller.accommodation.reservation.api;

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

import com.encore.petandbe.controller.accommodation.reservation.requests.DeleteReservationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.reservation.responses.DeleteReservationResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationDetailsResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationRetrieveResponse;
import com.encore.petandbe.service.accommodation.reservation.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@PostMapping
	public ResponseEntity<ReservationDetailsResponse> registerReservation(
		ReservationRegistrationRequest reservationRegistrationRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(reservationService.registerReservation(reservationRegistrationRequest));
	}

	@GetMapping("/{reservation-id}")
	public ResponseEntity<ReservationRetrieveResponse> retrieveReservation(
		@PathVariable("reservation-id") Long reservationId) {
		return ResponseEntity.ok().body(reservationService.findbyReservationId(reservationId));
	}

	@PutMapping("/{reservation-id}")
	public ResponseEntity<ReservationDetailsResponse> updateReservation(
		@PathVariable("reservation-id") Long reservationId,
		@RequestBody ReservationUpdatingRequest reservationUpdatingRequest) {
		return ResponseEntity.ok()
			.body(reservationService.updateReservation(reservationId, reservationUpdatingRequest));
	}

	@DeleteMapping("/{reservation-id}")
	public ResponseEntity<DeleteReservationResponse> deleteReservation(
		@PathVariable("reservation-id") Long reservationId, @RequestBody
	DeleteReservationRequest deleteReservationRequest) {
		return ResponseEntity.ok().body(reservationService.deleteReservation(new DeleteReservationRequest(
			deleteReservationRequest.getUserId(), reservationId)));
	}
}
