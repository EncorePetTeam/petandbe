package com.encore.petandbe.service.accommodation.accomodation.dto;

import java.time.LocalTime;

import com.encore.petandbe.model.accommodation.accommodation.AccommodationType;
import com.encore.petandbe.model.accommodation.address.Address;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationUpdatingDTO {
	private Address address;
	private String accommodationName;
	private LocalTime workingStart;
	private LocalTime workingEnd;
	private LocalTime weekendWorkingStart;
	private LocalTime weekendWorkingEnd;
	private String location;
	private String lotNumber;
	private String addressDetail;
	private AccommodationType accommodationType;
	private String detailInfo;

	private AccommodationUpdatingDTO(Address address, String accommodationName, LocalTime workingStart,
		LocalTime workingEnd, LocalTime weekendWorkingStart, LocalTime weekendWorkingEnd, String location,
		String lotNumber, String addressDetail,
		AccommodationType accommodationType, String detailInfo) {
		this.address = address;
		this.accommodationName = accommodationName;
		this.workingStart = workingStart;
		this.workingEnd = workingEnd;
		this.weekendWorkingStart = weekendWorkingStart;
		this.weekendWorkingEnd = weekendWorkingEnd;
		this.location = location;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.accommodationType = accommodationType;
		this.detailInfo = detailInfo;
	}
}
