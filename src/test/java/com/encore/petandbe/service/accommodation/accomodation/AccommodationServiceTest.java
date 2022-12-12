package com.encore.petandbe.service.accommodation.accomodation;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.accommodation.responses.AccommodationRetrievalResponse;
import com.encore.petandbe.exception.WrongTimeException;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.accommodation.AccommodationType;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.service.accommodation.accomodation.dto.AccommodationUpdatingDTO;

@SpringBootTest
@Transactional
class AccommodationServiceTest {

	@Autowired
	private AccommodationService accommodationService;
	@Autowired
	private AccommodationRepository accommodationRepository;

	private final String addressCode = "05300";
	private final Long userId = 1L;
	private final String accommodationName = "김훈기 애견 호텔";
	private final String workingHours = "09001600";
	private final String weekendWorkingHours = "15001900";
	private final String location = "홍제동";
	private final String lotNumber = "330-245";
	private final String addressDetail = "해주빌라 102호";
	private final AccommodationType accommodationType = AccommodationType.HOTEL;
	private final String detailInfo = "상세정보 입니다.";

	@Test
	@DisplayName("Creating Accommodation Service - Success")
	void createAccommodationTestSuccess() {
		//given
		AccommodationRegistrationRequest request = new AccommodationRegistrationRequest(
			addressCode, accommodationName, workingHours, weekendWorkingHours, location, lotNumber,
			addressDetail, accommodationType, detailInfo);
		//when
		Long accommodationId = accommodationService.createAccommodation(request, userId);
		Optional<Accommodation> accommodation = accommodationRepository.findById(accommodationId);
		//then
		assertThat(accommodation).isNotNull();
	}

	@Test
	@DisplayName("Updating Accommodation Service - Success")
	void updateAccommodationTestSuccess() {
		//given
		Accommodation accommodation = accommodationRepository.findById(1L).get();
		//when
		accommodation.updateAccommodation(AccommodationUpdatingDTO.builder()
			.accommodationType(accommodationType)
			.accommodationName(accommodationName)
			.addressDetail(addressDetail)
			.address(accommodation.getAddress())
			.workingStart(accommodation.getWorkingStart())
			.workingEnd(accommodation.getWorkingEnd())
			.weekendWorkingStart(accommodation.getWeekendWorkingStart())
			.weekendWorkingEnd(accommodation.getWeekendWorkingEnd())
			.location(location)
			.lotNumber(lotNumber)
			.detailInfo(detailInfo)
			.build());
		//then
		assertThat(accommodation.getAccommodationType()).isEqualTo(accommodationType);
		assertThat(accommodation.getAccommodationName()).isEqualTo(accommodationName);
		assertThat(accommodation.getLocation()).isEqualTo(location);
		assertThat(accommodation.getLotNumber()).isEqualTo(lotNumber);
		assertThat(accommodation.getDetailInfo()).isEqualTo(detailInfo);
	}

	@Test
	@DisplayName("Deleting Accommodation Service - Success")
	void deleteAccommodationByStatusTestSuccess() {
		//given
		Accommodation accommodation = accommodationRepository.findById(1L).get();
		//when
		accommodation.deleteAccommodation();
		//then
		assertThat(accommodation.getState()).isTrue();
	}

	@Test
	@DisplayName("Finding Accommodation Service - Success")
	void findAccommodationByIdTestSuccess() {
		//given //when
		AccommodationRetrievalResponse accommodation = accommodationService.findAccommodationById(1L);
		//then
		assertThat(accommodation).isNotNull();

	}

	@Test
	@DisplayName("Hour Boundary Test - Success")
	void validateHourTestSuccess() {
		//given
		String testWorkingHours = "00002300";
		AccommodationRegistrationRequest request = new AccommodationRegistrationRequest(
			addressCode, accommodationName, testWorkingHours, weekendWorkingHours, location, lotNumber,
			addressDetail, accommodationType, detailInfo);
		//when
		Long accommodationId = accommodationService.createAccommodation(request, userId);
		//then
		assertThat(accommodationRepository.findById(accommodationId)).isPresent();
	}

	@Test
	@DisplayName("Hour Out Of Range - Fail")
	void validateHourTestFail1() {
		//given
		String testWorkingHours = "03002400";
		AccommodationRegistrationRequest request = new AccommodationRegistrationRequest(
			addressCode, accommodationName, testWorkingHours, weekendWorkingHours, location, lotNumber,
			addressDetail, accommodationType, detailInfo);
		//then //when
		assertThrows(WrongTimeException.class, () -> accommodationService.createAccommodation(request, userId));
	}

	@Test
	@DisplayName("Minute Out Of Range - Fail")
	void validateMinuteTestFail() {
		//given
		String testWorkingHours = "03002361";
		AccommodationRegistrationRequest request = new AccommodationRegistrationRequest(
			addressCode, accommodationName, testWorkingHours, weekendWorkingHours, location, lotNumber,
			addressDetail, accommodationType, detailInfo);
		//then //when
		assertThrows(WrongTimeException.class, () -> accommodationService.createAccommodation(request, userId));
	}

	@Test
	@DisplayName("None Number Test - Fail")
	void isDigitTimeTestFail() {
		//given
		String testWorkingHours = "0000a000";
		AccommodationRegistrationRequest request = new AccommodationRegistrationRequest(
			addressCode, accommodationName, testWorkingHours, weekendWorkingHours, location, lotNumber,
			addressDetail, accommodationType, detailInfo);
		//when //then
		assertThrows(WrongTimeException.class, () -> accommodationService.createAccommodation(request, userId));
	}

	@Test
	@DisplayName("Time Length Over Test - Fail")
	void validateTimeTestFail() {
		//given
		String time = "000012000";
		AccommodationRegistrationRequest request = new AccommodationRegistrationRequest(
			addressCode, accommodationName, time, weekendWorkingHours, location, lotNumber,
			addressDetail, accommodationType, detailInfo);
		//when//then
		assertThrows(WrongTimeException.class, () -> accommodationService.createAccommodation(request, userId));
	}

	@Test
	@DisplayName("Start Time Grater Than End Time Test - Fail")
	void validateTimeTestFail2() {
		//given
		String time = "13000100";
		AccommodationRegistrationRequest request = new AccommodationRegistrationRequest(
			addressCode, accommodationName, time, weekendWorkingHours, location, lotNumber,
			addressDetail, accommodationType, detailInfo);
		//when //then
		assertThrows(WrongTimeException.class, () -> accommodationService.createAccommodation(request, userId));
	}
}