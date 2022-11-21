package com.encore.petandbe.service.accommodation.accomodation;

import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.accommodation.responses.AccommodationRetrievalResponse;
import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationUpdatingRequest;

@Service
public class AccommodationService {

	public Long createAccommodation(AccommodationRegistrationRequest accommodationRegistrationRequest) {
		return null;
	}

	public Long updateAccommodation(AccommodationUpdatingRequest accommodationUpdatingRequest, Long accommodationId) {
		return null;
	}

	public Long deleteAccommodationByStatus(Long accommodationId) {
		return null;
	}

	public AccommodationRetrievalResponse findAccommodationById(Long accommodationId) {
		return null;
	}
}
