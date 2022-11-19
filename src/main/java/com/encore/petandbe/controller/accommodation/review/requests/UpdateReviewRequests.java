package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class UpdateReviewRequests {

    private Long reviewId;
    private Long userId;
    private Integer rate;
    private String content;

    public UpdateReviewRequests(Long reviewId, Long userId, Integer rate, String content) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.rate = rate;
        this.content = content;
    }
}
