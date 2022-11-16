package com.encore.petandbe.controller.accommodation.review.responses;

import lombok.Getter;

@Getter
public class ReviewDetailsResponse {

    private Long reviewId;
    private String userId;
    private Integer rate;
    private String content;

    public ReviewDetailsResponse(Long reviewId, String userId, Integer rate, String content) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.rate = rate;
        this.content = content;
    }
}
