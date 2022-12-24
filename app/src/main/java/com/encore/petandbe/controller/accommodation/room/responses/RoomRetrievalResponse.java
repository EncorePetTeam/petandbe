package com.encore.petandbe.controller.accommodation.room.responses;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoomRetrievalResponse {

	private Long accommodationId;
	private String roomName;
	private Integer amount;
	private PetCategory petCategory;
	private String weight;
	private String detailInfo;
	private List<String> imageFileUrlList;

	public RoomRetrievalResponse(Long accommodationId, String roomName, Integer amount, PetCategory petCategory, String weight, String detailInfo, List<String> imageFileUrlList) {
		this.accommodationId = accommodationId;
		this.roomName = roomName;
		this.amount = amount;
		this.petCategory = petCategory;
		this.weight = weight;
		this.detailInfo = detailInfo;
		this.imageFileUrlList = imageFileUrlList;
	}
}
