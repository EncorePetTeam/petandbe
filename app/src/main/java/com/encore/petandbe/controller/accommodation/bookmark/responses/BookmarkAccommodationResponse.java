package com.encore.petandbe.controller.accommodation.bookmark.responses;

import lombok.Getter;

@Getter
public class BookmarkAccommodationResponse {

	private Long accommodationId;
	private String accommodationName;
	private String address;
	private double avgRate;

	public BookmarkAccommodationResponse(Long accommodationId, String accommodationName, String address,
		double avgRate) {
		this.accommodationId = accommodationId;
		this.accommodationName = accommodationName;
		this.address = address;
		this.avgRate = avgRate;
	}
}
