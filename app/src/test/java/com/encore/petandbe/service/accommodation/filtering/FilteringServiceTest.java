package com.encore.petandbe.service.accommodation.filtering;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.filtering.requests.FilteringAccommodationRequests;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationListResponse;
import com.encore.petandbe.model.accommodation.filtering.category.SortCategory;

@SpringBootTest
@Transactional
class FilteringServiceTest {

	@Autowired
	private FilteringService filteringService;

	private Long userId = 1L;

	@Test
	@DisplayName("filtering without filter - success")
	void filteringWithoutFilterSuccess() {
		//given
		Integer page = 1;

		FilteringAccommodationRequests filteringAccommodationRequests = new FilteringAccommodationRequests(null, null,
			null, null, null, null, page);
		//when
		FilteringAccommodationListResponse filteringAccommodationListResponse = filteringService.filteringAccommodation(
			userId,
			filteringAccommodationRequests);
		//then
		assertNotNull(filteringAccommodationListResponse);
	}

	@Test
	@DisplayName("filtering - success")
	void filteringSuccess() {
		//given
		Integer page = 1;
		String address = "05300";
		String checkIn = "2022-11-23 13:00:00";
		String checkOut = "2022-11-24 18:00:00";
		String petCategory = "DOG";
		String weight = "5.8";
		SortCategory sortCategory = SortCategory.NEWEST;

		FilteringAccommodationRequests filteringAccommodationRequests = new FilteringAccommodationRequests(address,
			checkIn, checkOut, petCategory, weight, sortCategory, page);
		//when
		FilteringAccommodationListResponse filteringAccommodationListResponse = filteringService.filteringAccommodation(
			userId,
			filteringAccommodationRequests);

		//then
		assertNotNull(filteringAccommodationListResponse);
	}

	@Test
	@DisplayName("filtering 2 - success")
	void filteringSuccessTwo() {
		//given
		Integer page = 1;
		String address = "05300";
		String checkIn = "2022-11-26 13:30:00";
		String checkOut = "2022-11-28 15:30:00";
		String petCategory = "CAT";
		String weight = "4.3";
		SortCategory sortCategory = SortCategory.AVERAGE;

		FilteringAccommodationRequests filteringAccommodationRequests = new FilteringAccommodationRequests(address,
			checkIn, checkOut, petCategory, weight, sortCategory, page);
		//when
		FilteringAccommodationListResponse filteringAccommodationListResponse = filteringService.filteringAccommodation(
			userId,
			filteringAccommodationRequests);

		//then
		assertNotNull(filteringAccommodationListResponse);
	}

	@Test
	@DisplayName("filtering 3 - success")
	void filteringSuccessThree() {
		//given
		Integer page = 1;
		String address = "05300";
		String checkIn = "2022-11-26 13:30:00";
		String checkOut = "2022-11-28 16:00:00";
		String petCategory = "CAT";
		String weight = "4.3";
		SortCategory sortCategory = SortCategory.BOOKMARK;

		FilteringAccommodationRequests filteringAccommodationRequests = new FilteringAccommodationRequests(address,
			checkIn, checkOut, petCategory, weight, sortCategory, page);
		//when
		FilteringAccommodationListResponse filteringAccommodationListResponse = filteringService.filteringAccommodation(
			userId,
			filteringAccommodationRequests);

		//then
		assertEquals(3L,
			filteringAccommodationListResponse.getFilteringAccommodationList().get(0).getAccommodationId());
	}
}