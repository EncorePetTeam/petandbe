package com.encore.petandbe.controller.accommodation.filtering.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.accommodation.filtering.requests.FilteringAccommodationRequests;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationListResponse;
import com.encore.petandbe.service.accommodation.filtering.FilteringService;

@RestController
@RequestMapping("/filtering")
public class FilteringController {

	private final FilteringService filteringService;

	public FilteringController(FilteringService filteringService) {
		this.filteringService = filteringService;
	}

	@GetMapping("/accommodation")
	public ResponseEntity<FilteringAccommodationListResponse> filteringAccommodation(
		@ModelAttribute FilteringAccommodationRequests filteringAccommodationRequests) {
		return ResponseEntity.ok()
			.body(filteringService.filteringAccommodation(filteringAccommodationRequests));
	}
}
