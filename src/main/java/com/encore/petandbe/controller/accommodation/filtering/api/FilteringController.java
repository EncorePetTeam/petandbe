package com.encore.petandbe.controller.accommodation.filtering.api;

import com.encore.petandbe.service.accommodation.filtering.FilteringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
