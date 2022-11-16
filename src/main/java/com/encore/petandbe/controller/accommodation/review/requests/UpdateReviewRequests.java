package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class UpdateReviewRequests {

    private Long reviewId;
    private String userId;
    private Integer rate;
    private String content;

    public UpdateReviewRequests(Long reviewId, String userId, Integer rate, String content) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.rate = rate;
        this.content = content;
    }
}
