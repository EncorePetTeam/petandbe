package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;

@Getter
public class RegistReviewRequests {

    private Long userId;
    private Integer rate;
    private String content;
    private Long reservationId;

    public RegistReviewRequests(Long userId, Integer rate, String content, Long reservationId) {
        this.userId = userId;
        this.rate = rate;
        this.content = content;
        this.reservationId = reservationId;
    }
}
