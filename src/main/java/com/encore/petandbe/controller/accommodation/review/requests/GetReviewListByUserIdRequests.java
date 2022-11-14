package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class GetReviewListByUserIdRequests {

    private String userId;
    private int pageNum;
    private int amount;

    public GetReviewListByUserIdRequests(String userId, int pageNum, int amount) {
        this.userId = userId;
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
