package com.encore.petandbe.controller.accommodation.review.responses;

import lombok.Getter;

@Getter
public class GetReviewListByUserIdResponse {
    private int endPage;
    private int startPage;
    private boolean nextPageExist;
    private boolean prevPageExist;
    private int selectPage;
    private int amountItem;

    public GetReviewListByUserIdResponse(int endPage, int startPage, boolean nextPageExist, boolean prevPageExist, int selectPage, int amountItem) {
        this.endPage = endPage;
        this.startPage = startPage;
        this.nextPageExist = nextPageExist;
        this.prevPageExist = prevPageExist;
        this.selectPage = selectPage;
        this.amountItem = amountItem;
    }
}
