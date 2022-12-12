package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class UpdateReviewRequests {

    private Long userId;
    private Integer rate;
    private String content;

    public UpdateReviewRequests(Long userId, Integer rate, String content) {
        this.userId = userId;
        this.rate = rate;
        this.content = content;
    }
}
