package com.encore.petandbe.service.accommodation.room.dto;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomUpdateDTO {

	private final String roomName;
	private final Integer amount;
	private final PetCategory petCategory;
	private final String weight;
	private final String detailInfo;

}
