package com.encore.petandbe.controller.accommodation.bookmark.responses;

import lombok.Getter;

@Getter
public class BookmarkDetailsResponse {

	private Long userId;
	private Long accommodationId;
	private boolean state;

	public BookmarkDetailsResponse(Long userId, Long accommodationId, boolean state) {
		this.userId = userId;
		this.accommodationId = accommodationId;
		this.state = state;
	}
}
