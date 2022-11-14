package com.encore.petandbe.controller.accommodation.filtering.requests;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FilteringResearchRequests {

    private String address;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Enum<PetCategory> petCategory;
    private String weight;
    private String sortCategory;

    public FilteringResearchRequests(String address, LocalDateTime checkIn, LocalDateTime checkOut, Enum<PetCategory> petCategory, String weight, String sortCategory) {
        this.address = address;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.petCategory = petCategory;
        this.weight = weight;
        this.sortCategory = sortCategory;
    }
}
