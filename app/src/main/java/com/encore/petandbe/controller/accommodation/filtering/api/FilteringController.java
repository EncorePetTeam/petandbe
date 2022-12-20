package com.encore.petandbe.controller.accommodation.filtering.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.accommodation.filtering.requests.FilteringAccommodationRequests;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationListResponse;
import com.encore.petandbe.model.user.user.Role;
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
		HttpServletRequest httpServletRequest,
		@ModelAttribute FilteringAccommodationRequests filteringAccommodationRequests) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());

		Long convertLongUserId = userId != null ? Long.valueOf(userId) : null;
		
		return ResponseEntity.ok()
			.body(filteringService.filteringAccommodation(convertLongUserId, filteringAccommodationRequests));
	}
}
