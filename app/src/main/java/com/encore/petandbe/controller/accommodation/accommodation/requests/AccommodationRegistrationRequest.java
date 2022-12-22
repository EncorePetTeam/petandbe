package com.encore.petandbe.controller.accommodation.accommodation.requests;

import java.util.List;

import com.encore.petandbe.model.accommodation.accommodation.AccommodationType;

import lombok.Getter;

@Getter
public class AccommodationRegistrationRequest {

	private final String addressCode;
	private final String accommodationName;
	private final String workingHours;
	private final String weekendWorkingHours;
	private final String location;
	private final String lotNumber;
	private final String addressDetail;
	private final AccommodationType accommodationType;
	private final String detailInfo;
	private final List<String> imageUrlList;

	public AccommodationRegistrationRequest(String addressCode, String accommodationName, String workingHours,
		String weekendWorkingHours, String location, String lotNumber, String addressDetail,
		AccommodationType accommodationType, String detailInfo, List<String> imageUrlList) {
		this.addressCode = addressCode;
		this.accommodationName = accommodationName;
		this.workingHours = workingHours;
		this.weekendWorkingHours = weekendWorkingHours;
		this.location = location;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.accommodationType = accommodationType;
		this.detailInfo = detailInfo;
		this.imageUrlList = imageUrlList;
	}
}
