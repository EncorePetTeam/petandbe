package com.encore.petandbe.controller.accommodation.room.responses;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoomRetrievalInfo {

    private Long roomId;
    private String roomName;
    private Integer amount;
    private PetCategory petCategory;
    private String weight;
    private String detailInfo;
    private List<String> imageFileUrlList;

    public RoomRetrievalInfo(Long roomId, String roomName, Integer amount, PetCategory petCategory, String weight, String detailInfo, List<String> imageFileUrlList) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.amount = amount;
        this.petCategory = petCategory;
        this.weight = weight;
        this.detailInfo = detailInfo;
        this.imageFileUrlList = imageFileUrlList;
    }
}
