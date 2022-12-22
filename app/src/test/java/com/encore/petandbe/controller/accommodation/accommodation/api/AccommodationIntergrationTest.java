package com.encore.petandbe.controller.accommodation.accommodation.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.accommodation.responses.AccommodationRetrievalResponse;

@SpringBootTest
@Transactional
class AccommodationIntegrationTest {

	@Autowired
	private AccommodationController accommodationController;

	private final Long accommodationId = 1L;

	@Test
	@DisplayName("Retrieve Accommodation Integration Controller - Success")
	void retrieveAccommodationIntegrationTestSuccess() {
		//given //when
		ResponseEntity<AccommodationRetrievalResponse> response = accommodationController.retrieveAccommodation(
			accommodationId);
		//then
		assertFalse(response.getBody().getImageFileUrlList().isEmpty());
	}
}
