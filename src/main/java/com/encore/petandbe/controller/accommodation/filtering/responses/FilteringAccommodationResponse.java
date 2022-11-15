package com.encore.petandbe.controller.accommodation.filtering.responses;

import lombok.Getter;

@Getter
public class FilteringAccommodationResponse {

    private Long accommodationId;
    private String address;
    private String accommodationName;
    private Integer avgRate;

    public FilteringAccommodationResponse(Long accommodationId, String address, String accommodationName, Integer avgRate) {
        this.accommodationId = accommodationId;
        this.address = address;
        this.accommodationName = accommodationName;
        this.avgRate = avgRate;
    }
}
