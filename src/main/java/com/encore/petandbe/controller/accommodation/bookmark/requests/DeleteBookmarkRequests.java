package com.encore.petandbe.controller.accommodation.bookmark.requests;

import lombok.Getter;

@Getter
public class DeleteBookmarkRequests {

	private Long accommodationId;
	private Long userId;

	public DeleteBookmarkRequests(Long accommodationId, Long userId) {
		this.accommodationId = accommodationId;
		this.userId = userId;
	}
}
