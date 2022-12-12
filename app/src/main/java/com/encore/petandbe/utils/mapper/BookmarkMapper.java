package com.encore.petandbe.utils.mapper;

import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkDetailsResponse;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.bookmark.Bookmark;
import com.encore.petandbe.model.accommodation.bookmark.BookmarkId;
import com.encore.petandbe.model.user.user.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkMapper {

	private static BookmarkMapper bookmarkMapper;

	public static BookmarkMapper of() {
		if (bookmarkMapper == null) {
			bookmarkMapper = new BookmarkMapper();
		}
		return bookmarkMapper;
	}

	public BookmarkId requestToBookmarkId(User user, Accommodation accommodation) {
		return BookmarkId.builder().user(user).accommodation(accommodation).build();
	}

	public BookmarkDetailsResponse bookmarkToDetailsResponse(Bookmark bookmark) {
		return new BookmarkDetailsResponse(bookmark.getBookmarkId().getUser().getId(),
			bookmark.getBookmarkId().getAccommodation().getId(), bookmark.getState());
	}

	public Bookmark createBookmarkIfNotExistUpdateBookmarkIfExist(BookmarkId bookmarkId) {
		return Bookmark.builder().bookmarkId(bookmarkId).state(false).build();
	}
}
