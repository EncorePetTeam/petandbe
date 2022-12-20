package com.encore.petandbe.utils.mapper;

import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.accommodation.responses.AccommodationRetrievalResponse;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.address.Address;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.service.accommodation.accomodation.dto.AccommodationUpdatingDTO;
import com.encore.petandbe.utils.validator.TimeValidator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccommodationMapper {

	public static AccommodationRetrievalResponse convertAccommodationToRetrievalResponse(Accommodation accommodation) {
		return AccommodationRetrievalResponse.builder()
			.addressCode(accommodation.getAddress().getAddressCode())
			.accommodationName(accommodation.getAccommodationName())
			.userNickname(accommodation.getUser().getNickname())
			.workingHours(
				TimeValidator.convertLocalTimeToString(accommodation.getWorkingStart(), accommodation.getWorkingEnd()))
			.weekendWorkingHours(TimeValidator.convertLocalTimeToString(accommodation.getWeekendWorkingStart(),
				accommodation.getWeekendWorkingEnd()))
			.location(accommodation.getLocation())
			.lotNumber(accommodation.getLotNumber())
			.addressDetail(accommodation.getAddressDetail())
			.accommodationType(accommodation.getAccommodationType())
			.detailInfo(accommodation.getDetailInfo())
			.build();
	}

	public static AccommodationUpdatingDTO convertUpdatingRequestToUpdatingDTO(AccommodationUpdatingRequest request,
		Address address) {
		return AccommodationUpdatingDTO.builder()
			.address(address)
			.accommodationName(request.getAccommodationName())
			.workingStart(TimeValidator.parseStartTime(request.getWorkingHours()))
			.workingEnd(TimeValidator.parseEndTime(request.getWorkingHours()))
			.weekendWorkingStart(TimeValidator.parseStartTime(request.getWeekendWorkingHours()))
			.weekendWorkingEnd(TimeValidator.parseEndTime(request.getWeekendWorkingHours()))
			.location(request.getLocation())
			.lotNumber(request.getLotNumber())
			.addressDetail(request.getAddressDetail())
			.accommodationType(request.getAccommodationType())
			.detailInfo(request.getDetailInfo())
			.build();
	}

	public static Accommodation convertRegistrationRequestToAccommodation(
		AccommodationRegistrationRequest request, User user, Address address) {
		return Accommodation.builder()
			.address(address)
			.user(user)
			.accommodationName(request.getAccommodationName())
			.workingStart(TimeValidator.parseStartTime(request.getWorkingHours()))
			.workingEnd(TimeValidator.parseEndTime(request.getWorkingHours()))
			.weekendWorkingStart(TimeValidator.parseStartTime(request.getWeekendWorkingHours()))
			.weekendWorkingEnd(TimeValidator.parseEndTime(request.getWeekendWorkingHours()))
			.location(request.getLocation())
			.lotNumber(request.getLotNumber())
			.reviewCount(0)
			.bookmarkCount(0)
			.addressDetail(request.getAddressDetail())
			.accommodationType(request.getAccommodationType())
			.detailInfo(request.getDetailInfo())
			.build();
	}
}
