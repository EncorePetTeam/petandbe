package com.encore.petandbe.controller.accommodation.accommodation.requests;

import com.encore.petandbe.model.accommodation.accommodation.AccommodationType;

import lombok.Getter;

@Getter
public class AccommodationUpdatingRequest {

	private String addressCode;
	private Long userId;
	private String accommodationName;
	private String workingHours;
	private String weekendWorkingHours;
	private String location;
	private String lotNumber;
	private String addressDetail;
	private AccommodationType accommodationType;
	private String detailInfo;

	public AccommodationUpdatingRequest(String addressCode, Long userId, String accommodationName,
		String workingHours, String weekendWorkingHours, String location, String lotNumber, String addressDetail,
		AccommodationType accommodationType, String detailInfo) {
		this.addressCode = addressCode;
		this.userId = userId;
		this.accommodationName = accommodationName;
		this.workingHours = workingHours;
		this.weekendWorkingHours = weekendWorkingHours;
		this.location = location;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.accommodationType = accommodationType;
		this.detailInfo = detailInfo;
	}
}
