package com.encore.petandbe.controller.accommodation.review.api;

import com.encore.petandbe.controller.accommodation.review.requests.DeleteReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.RegistReviewRequests;
import com.encore.petandbe.controller.accommodation.review.requests.UpdateReviewRequests;
import com.encore.petandbe.controller.accommodation.review.responses.DeleteReviewResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.service.accommodation.review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{reservation-id}")
    public ResponseEntity<ReviewDetailsResponse> reviewDetails(@PathVariable("reservation-id") String reservationId){
        return ResponseEntity.ok().body(reviewService.findReviewDetails(reservationId));
    }

    @PostMapping("/update-review")
    public ResponseEntity<ReviewDetailsResponse> updateReview(@RequestBody UpdateReviewRequests updateReviewRequests){
        return ResponseEntity.ok().body(reviewService.updateReview(updateReviewRequests));
    }

    @PostMapping("/delete-review")
    public ResponseEntity<DeleteReviewResponse> deleteReview(@RequestBody DeleteReviewRequests deleteReviewRequests){
        return ResponseEntity.ok().body(reviewService.deleteReview(deleteReviewRequests));
    }
}
