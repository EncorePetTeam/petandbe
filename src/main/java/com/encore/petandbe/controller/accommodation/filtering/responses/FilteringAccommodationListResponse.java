package com.encore.petandbe.controller.accommodation.filtering.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class FilteringAccommodationListResponse {

    private List<FilteringAccommodationResponse> filteringAccommodationList;

    public FilteringAccommodationListResponse(List<FilteringAccommodationResponse> filteringAccommodationList) {
        this.filteringAccommodationList = filteringAccommodationList;
    }
}
