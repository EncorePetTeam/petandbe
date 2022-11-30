package com.encore.petandbe.controller.accommodation.bookmark.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.accommodation.bookmark.requests.BookmarkRegistrationRequests;
import com.encore.petandbe.controller.accommodation.bookmark.requests.DeleteBookmarkRequests;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkAccommodationListResponse;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkDetailsResponse;
import com.encore.petandbe.service.accommodation.bookmark.BookmarkService;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	public BookmarkController(BookmarkService bookmarkService) {
		this.bookmarkService = bookmarkService;
	}

	@PostMapping
	public ResponseEntity<BookmarkDetailsResponse> registerBookmark(
		@RequestBody BookmarkRegistrationRequests bookmarkRegistrationRequests) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(bookmarkService.registBookmark(bookmarkRegistrationRequests));
	}

	@DeleteMapping("/{accommodation-id}")
	public ResponseEntity<BookmarkDetailsResponse> deleteBookmark(
		@PathVariable("accommodation-id") Long accommodationId,
		@RequestBody DeleteBookmarkRequests deleteBookmarkRequests) {
		return ResponseEntity.ok()
			.body(bookmarkService.deleteBookmark(
				new DeleteBookmarkRequests(accommodationId, deleteBookmarkRequests.getUserId())));
	}

	@GetMapping("/{user-id}")
	public ResponseEntity<BookmarkAccommodationListResponse> accommodationListByBookmark(
		@PathVariable("user-id") Long userId) {
		return ResponseEntity.ok().body(bookmarkService.findAccommodationListByBookmark(userId));
	}
}
