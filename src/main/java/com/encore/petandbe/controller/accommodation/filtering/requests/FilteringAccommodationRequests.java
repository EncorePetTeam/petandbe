package com.encore.petandbe.controller.accommodation.filtering.requests;

import com.encore.petandbe.model.accommodation.filtering.category.SortCategory;

import lombok.Getter;

@Getter
public class FilteringAccommodationRequests {

	private String address;
	private String checkIn;
	private String checkOut;
	private String petCategory;
	private String weight;
	private SortCategory sortCategory;
	private Integer page;

	public FilteringAccommodationRequests(String address, String checkIn, String checkOut,
		String petCategory,
		String weight, SortCategory sortCategory, Integer page) {
		this.address = address;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.petCategory = petCategory;
		this.weight = weight;
		this.sortCategory = sortCategory;
		this.page = page;
	}
}
