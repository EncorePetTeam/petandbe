package com.encore.petandbe.controller.accommodation.review.responses;

import lombok.Getter;

@Getter
public class ReviewDetailsResponse {

    private String reviewId;
    private String userId;
    private String rate;
    private String content;

    public ReviewDetailsResponse(String reviewId, String userId, String rate, String content) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.rate = rate;
        this.content = content;
    }
}
