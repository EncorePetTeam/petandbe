package com.encore.petandbe.controller.accommodation.bookmark.responses;

import java.util.List;

import lombok.Getter;

@Getter
public class BookmarkAccommodationListResponse {

	private Long userId;
	private List<BookmarkAccommodationResponse> bookmarkAccommodationResponseList;

	public BookmarkAccommodationListResponse(Long userId,
		List<BookmarkAccommodationResponse> bookmarkAccommodationResponseList) {
		this.userId = userId;
		this.bookmarkAccommodationResponseList = bookmarkAccommodationResponseList;
	}
}
