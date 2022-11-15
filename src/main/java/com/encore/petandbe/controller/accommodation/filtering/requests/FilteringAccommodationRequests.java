package com.encore.petandbe.controller.accommodation.filtering.requests;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class FilteringAccommodationRequests {

    private String address;
    private String checkIn;
    private String checkOut;
    private String petCategory;
    private String weight;
    private String sortCategory;
    private int page;

    public FilteringAccommodationRequests() {}
}
