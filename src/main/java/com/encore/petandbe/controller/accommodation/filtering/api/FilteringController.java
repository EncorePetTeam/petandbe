package com.encore.petandbe.controller.accommodation.filtering.api;

import com.encore.petandbe.controller.accommodation.filtering.requests.FilteringAccommodationRequests;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationListResponse;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationResponse;
import com.encore.petandbe.service.accommodation.filtering.FilteringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filtering")
public class FilteringController {

    private final FilteringService filteringService;

    public FilteringController(FilteringService filteringService){
        this.filteringService = filteringService;
    }

    @GetMapping("/filtering-accommodation")
    public ResponseEntity<FilteringAccommodationListResponse> filteringAccommodation(FilteringAccommodationRequests filteringAccommodationRequests){
        System.out.println(filteringAccommodationRequests);
        return ResponseEntity.ok().body(filteringService.filteringAccommodation(filteringAccommodationRequests));
    }
}
