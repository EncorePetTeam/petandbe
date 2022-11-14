package com.encore.petandbe.controller.accommodation.review.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class RegistReviewRequests {

    private String userId;
    private String rate;
    private String content;
    private Long reservationId;

    public RegistReviewRequests(String userId, String rate, String content, Long reservationId) {
        this.userId = userId;
        this.rate = rate;
        this.content = content;
        this.reservationId = reservationId;
    }
}
