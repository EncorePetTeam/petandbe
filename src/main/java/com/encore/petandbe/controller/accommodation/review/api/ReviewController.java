package com.encore.petandbe.controller.accommodation.review.api;

import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.service.accommodation.review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/regist-review")
    public ResponseEntity<ReviewDetailsResponse> registReview(@RequestBody RegistReviewRequests registReviewRequests){
        return ResponseEntity.ok().body(reviewService.registReview(registReviewRequests));
    }

}
