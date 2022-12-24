package com.encore.petandbe.controller.accommodation.room.requests;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;

import lombok.Getter;

import java.util.List;

@Getter
public class RoomRegistrationRequest {

	private final Long accommodationId;
	private final String roomName;
	private final Integer amount;
	private final PetCategory petCategory;
	private final String weight;
	private final String detailInfo;
	private final List<String> imageUrlList;

	public RoomRegistrationRequest(Long accommodationId, String roomName, Integer amount,
								   PetCategory petCategory, String weight, String detailInfo, List<String> imageUrlList) {
		this.accommodationId = accommodationId;
		this.roomName = roomName;
		this.amount = amount;
		this.petCategory = petCategory;
		this.weight = weight;
		this.detailInfo = detailInfo;
		this.imageUrlList = imageUrlList;
	}
}
