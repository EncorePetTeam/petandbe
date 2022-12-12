package com.encore.petandbe.controller.accommodation.filtering.responses;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FilteringAccommodationResponse {

	private Long accommodationId;
	private String accommodationName;
	private String address;
	private double avgRate;

	public FilteringAccommodationResponse(Long accommodationId, String accommodationName, String address,
		double avgRate) {
		this.accommodationId = accommodationId;
		this.accommodationName = accommodationName;
		this.address = address;
		this.avgRate = avgRate;
	}
}
