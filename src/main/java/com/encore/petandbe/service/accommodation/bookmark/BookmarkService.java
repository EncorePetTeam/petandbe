package com.encore.petandbe.service.accommodation.bookmark;

import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.bookmark.requests.BookmarkRegistrationRequests;
import com.encore.petandbe.controller.accommodation.bookmark.requests.DeleteBookmarkRequests;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkAccommodationListResponse;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkDetailsResponse;

@Service
public class BookmarkService {
	public BookmarkDetailsResponse registBookmark(BookmarkRegistrationRequests bookmarkRegistrationRequests) {
		return null;
	}

	public BookmarkDetailsResponse deleteBookmark(DeleteBookmarkRequests deleteBookmarkRequests) {
		return null;
	}

	public BookmarkAccommodationListResponse findAccommodationListByBookmark(Long userId) {
		return null;
	}
}
