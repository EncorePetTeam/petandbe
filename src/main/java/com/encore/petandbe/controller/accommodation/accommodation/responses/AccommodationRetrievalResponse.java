package com.encore.petandbe.controller.accommodation.accommodation.responses;

import com.encore.petandbe.model.accommodation.accommodation.AccommodationType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationRetrievalResponse {
	private String addressCode;
	private String accommodationName;
	private String userNickname;
	private String workingHours;
	private String weekendWorkingHours;
	private String location;
	private String lotNumber;
	private String addressDetail;
	private AccommodationType accommodationType;
	private Double averageRate;
	private String detailInfo;

	public AccommodationRetrievalResponse(String addressCode, String accommodationName, String userNickname, String workingHours,
		String weekendWorkingHours, String location, String lotNumber, String addressDetail,
		AccommodationType accommodationType, Double averageRate, String detailInfo) {
		this.addressCode = addressCode;
		this.accommodationName = accommodationName;
		this.userNickname = userNickname;
		this.workingHours = workingHours;
		this.weekendWorkingHours = weekendWorkingHours;
		this.location = location;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.accommodationType = accommodationType;
		this.averageRate = averageRate;
		this.detailInfo = detailInfo;
	}
}
