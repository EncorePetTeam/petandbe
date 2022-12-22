package com.encore.petandbe.controller.accommodation.accommodation.api;

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
import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.accommodation.responses.AccommodationIdResponse;
import com.encore.petandbe.controller.accommodation.accommodation.responses.AccommodationRetrievalResponse;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.accomodation.AccommodationService;

@RestController
@RequestMapping("/accommodation")
public class AccommodationController {

	private final AccommodationService accommodationService;

	public AccommodationController(
		AccommodationService accommodationService) {
		this.accommodationService = accommodationService;
	}

	@Permission(role = Role.USER)
	@PostMapping
	public ResponseEntity<AccommodationIdResponse> registerAccommodation(@RequestBody
	AccommodationRegistrationRequest request, HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new AccommodationIdResponse(
				accommodationService.createAccommodation(request, Long.valueOf(userId))));
	}

	@PutMapping("/{accommodation-id}")
	@Permission(role = Role.USER)
	public ResponseEntity<AccommodationIdResponse> updateAccommodation(
		@PathVariable("accommodation-id") Long accommodationId,
		@RequestBody AccommodationUpdatingRequest request) {
		return ResponseEntity.ok()
			.body(new AccommodationIdResponse(accommodationService.updateAccommodation(request, accommodationId)));
	}

	@DeleteMapping("/{accommodation-id}")
	@Permission(role = Role.USER)
	public ResponseEntity<AccommodationIdResponse> deleteAccommodation(
		@PathVariable("accommodation-id") Long accommodationId) {
		return ResponseEntity.ok()
			.body(new AccommodationIdResponse(accommodationService.deleteAccommodationByStatus(accommodationId)));
	}

	@GetMapping("/{accommodation-id}")
	public ResponseEntity<AccommodationRetrievalResponse> retrieveAccommodation(
		@PathVariable("accommodation-id") Long accommodationId) {
		return ResponseEntity.ok().body(accommodationService.findAccommodationById(accommodationId));
	}
}
