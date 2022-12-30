package com.encore.petandbe.controller.accommodation.room.responses;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
public class RoomInfoResponse {

    private Long accommodationId;
    private List<RoomRetrievalInfo> roomRetrievalInfos;

    public RoomInfoResponse(Long accommodationId, List<RoomRetrievalInfo> roomRetrievalInfos) {
        this.accommodationId = accommodationId;
        this.roomRetrievalInfos = roomRetrievalInfos;
    }
}
