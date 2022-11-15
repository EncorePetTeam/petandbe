package com.encore.petandbe.controller.accommodation.filtering.api;

import com.encore.petandbe.controller.accommodation.filtering.requests.FilteringAccommodationRequests;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationResponse;
import com.encore.petandbe.service.accommodation.filtering.FilteringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/filtering")
public class FilteringController {

    private final FilteringService filteringService;

    public FilteringController(FilteringService filteringService){
        this.filteringService = filteringService;
    }

    @GetMapping("/{address}/{checkIn}/{checkOut}/{petCategory}/{weight}/{sortCategory}/{page}")
    public ResponseEntity<FilteringAccommodationResponse> filteringAccommodation(@PathVariable FilteringAccommodationRequests filteringAccommodationRequests){
        return ResponseEntity.ok().body(filteringService.filteringAccommodation(filteringAccommodationRequests));
    }
}
