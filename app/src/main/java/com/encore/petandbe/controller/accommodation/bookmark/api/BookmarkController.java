package com.encore.petandbe.controller.accommodation.bookmark.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.config.Permission;
import com.encore.petandbe.controller.accommodation.bookmark.requests.BookmarkRegistrationRequests;
import com.encore.petandbe.controller.accommodation.bookmark.requests.DeleteBookmarkRequests;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkAccommodationListResponse;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkDetailsResponse;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.bookmark.BookmarkService;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	public BookmarkController(BookmarkService bookmarkService) {
		this.bookmarkService = bookmarkService;
	}

	@Permission(role = Role.USER)
	@PostMapping
	public ResponseEntity<BookmarkDetailsResponse> registerBookmark(
		@RequestBody BookmarkRegistrationRequests bookmarkRegistrationRequests, HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(bookmarkService.registerBookmark(new BookmarkRegistrationRequests(
				bookmarkRegistrationRequests.getAccommodationId(), Long.valueOf(userId))));
	}

	@Permission(role = Role.USER)
	@DeleteMapping("/{accommodation-id}")
	public ResponseEntity<BookmarkDetailsResponse> deleteBookmark(
		@PathVariable("accommodation-id") Long accommodationId, HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());
		return ResponseEntity.ok()
			.body(bookmarkService.deleteBookmark(new DeleteBookmarkRequests(accommodationId, Long.valueOf(userId))));
	}

	@Permission(role = Role.USER)
	@GetMapping("/list")
	public ResponseEntity<BookmarkAccommodationListResponse> accommodationListByBookmark(
		HttpServletRequest httpServletRequest) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());
		return ResponseEntity.ok().body(bookmarkService.findAccommodationListByBookmark(Long.valueOf(userId)));
	}
}
