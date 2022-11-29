package com.encore.petandbe.controller.accommodation.room.requests;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;

import lombok.Getter;

@Getter
public class RoomUpdateRequest {

	private Long accommodationId;
	private String roomName;
	private Integer amount;
	private PetCategory petCategory;
	private String weight;
	private String detailInfo;

	public RoomUpdateRequest(Long accommodationId, String roomName, Integer amount,
		PetCategory petCategory, String weight, String detailInfo) {
		this.accommodationId = accommodationId;
		this.roomName = roomName;
		this.amount = amount;
		this.petCategory = petCategory;
		this.weight = weight;
		this.detailInfo = detailInfo;
	}
}
