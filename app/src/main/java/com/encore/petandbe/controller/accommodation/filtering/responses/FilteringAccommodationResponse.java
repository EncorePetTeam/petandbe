package com.encore.petandbe.controller.accommodation.filtering.responses;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FilteringAccommodationResponse {

	private Long accommodationId;
	private String accommodationName;
	private String addressCode;
	private String location;
	private String lotNumber;
	private double avgRate;
	private boolean isBookmarked;
	private String imageUrl;
	private Integer roomAmount;

	public FilteringAccommodationResponse(Long accommodationId, String accommodationName, String addressCode,
		String location, String lotNumber, double avgRate, boolean isBookmarked, String imageUrl, Integer roomAmount) {
		this.accommodationId = accommodationId;
		this.accommodationName = accommodationName;
		this.addressCode = addressCode;
		this.location = location;
		this.lotNumber = lotNumber;
		this.avgRate = avgRate;
		this.isBookmarked = isBookmarked;
		this.imageUrl = imageUrl;
		this.roomAmount = roomAmount;
	}
}
