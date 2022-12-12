package com.encore.petandbe.controller.accommodation.bookmark.requests;

import lombok.Getter;

@Getter
public class BookmarkRegistrationRequests {

	private Long accommodationId;
	private Long userId;

	public BookmarkRegistrationRequests(Long accommodationId, Long userId) {
		this.accommodationId = accommodationId;
		this.userId = userId;
	}
}
