package com.encore.petandbe.service.accommodation.filtering;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.filtering.requests.FilteringAccommodationRequests;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationListResponse;
import com.encore.petandbe.model.accommodation.filtering.category.SortCategory;

@SpringBootTest
@Transactional
class FilteringServiceTest {

	@Autowired
	private FilteringService filteringService;

	@Test
	@DisplayName("filtering without filter - success")
	void filteringWithoutFilterSuccess() {
		//given
		Integer page = 1;

		FilteringAccommodationRequests filteringAccommodationRequests = new FilteringAccommodationRequests(null, null,
			null, null, null, null, page);
		//when
		FilteringAccommodationListResponse filteringAccommodationListResponse = filteringService.filteringAccommodation(
			filteringAccommodationRequests);
		//then
		assertEquals(4L,
			filteringAccommodationListResponse.getFilteringAccommodationList().get(0).getAccommodationId());
		assertEquals(3L,
			filteringAccommodationListResponse.getFilteringAccommodationList().get(1).getAccommodationId());
		assertEquals(2L,
			filteringAccommodationListResponse.getFilteringAccommodationList().get(2).getAccommodationId());
		assertEquals(1L,
			filteringAccommodationListResponse.getFilteringAccommodationList().get(3).getAccommodationId());
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
			filteringAccommodationRequests);

		//then
		assertEquals(1L,
			filteringAccommodationListResponse.getFilteringAccommodationList().get(0).getAccommodationId());
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
			filteringAccommodationRequests);

		//then
		assertEquals(3L,
			filteringAccommodationListResponse.getFilteringAccommodationList().get(0).getAccommodationId());
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
			filteringAccommodationRequests);

		//then
		assertEquals(3L,
			filteringAccommodationListResponse.getFilteringAccommodationList().get(0).getAccommodationId());
	}
}