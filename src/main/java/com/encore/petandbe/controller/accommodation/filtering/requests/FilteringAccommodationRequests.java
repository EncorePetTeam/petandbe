package com.encore.petandbe.controller.accommodation.filtering.requests;

import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FilteringAccommodationRequests {

    private String address;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Enum<PetCategory> petCategory;
    private String weight;
    private String sortCategory;
    private int page;

    public FilteringAccommodationRequests(String address, LocalDateTime checkIn, LocalDateTime checkOut, Enum<PetCategory> petCategory, String weight, String sortCategory, int page) {
        this.address = address;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.petCategory = petCategory;
        this.weight = weight;
        this.sortCategory = sortCategory;
        this.page = page;
    }
}
