package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class DeleteReviewRequests {

    private Long reviewId;
    private String userId;

    public DeleteReviewRequests(Long reviewId, String userId) {
        this.reviewId = reviewId;
        this.userId = userId;
    }
}
