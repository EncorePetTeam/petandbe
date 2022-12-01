package com.encore.petandbe.service.accommodation.bookmark;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.bookmark.requests.BookmarkRegistrationRequests;
import com.encore.petandbe.controller.accommodation.bookmark.requests.DeleteBookmarkRequests;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkDetailsResponse;
import com.encore.petandbe.exception.NonExistResourceException;

@SpringBootTest
@Transactional
class BookmarkServiceSpringBootTest {

	@Autowired
	private BookmarkService bookmarkService;

	Long existUserId = 5L;
	Long existAccommodationId = 4L;
	Long accommodationIdForDeleteTest = 2L;
	Long notExistUserId = 1242151L;
	Long noteExistAccommodationId = 1252164365L;

	@Test
	@DisplayName("Register bookmark - success")
	void registerBookmarkSuccess() {
		//given
		BookmarkRegistrationRequests bookmarkRegistrationRequests = new BookmarkRegistrationRequests(
			existAccommodationId,
			existUserId);

		//when
		BookmarkDetailsResponse bookmarkDetailsResponse = bookmarkService.registerBookmark(
			bookmarkRegistrationRequests);
		//then
		assertEquals(existUserId, bookmarkDetailsResponse.getUserId());
		assertEquals(existAccommodationId, bookmarkDetailsResponse.getAccommodationId());
	}

	@Test
	@DisplayName("Register bookmark - user does not exist fail")
	void registerBookmarkUserDoesNotExistFail() {
		//given
		BookmarkRegistrationRequests bookmarkRegistrationRequests = new BookmarkRegistrationRequests(
			noteExistAccommodationId,
			notExistUserId);

		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			bookmarkService.registerBookmark(bookmarkRegistrationRequests);
		});
	}

	@Test
	@DisplayName("Register bookmark - accommodation does not exist fail")
	void registerBookmarkAccommodationDoesNotExistFail() {
		//given
		BookmarkRegistrationRequests bookmarkRegistrationRequests = new BookmarkRegistrationRequests(
			noteExistAccommodationId,
			notExistUserId);

		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			bookmarkService.registerBookmark(bookmarkRegistrationRequests);
		});
	}

	@Test
	@DisplayName("Delete bookmark - success")
	void deleteBookmarkSuccess() {
		//given
		DeleteBookmarkRequests deleteBookmarkRequests = new DeleteBookmarkRequests(accommodationIdForDeleteTest,
			existUserId);

		//when
		BookmarkDetailsResponse bookmarkDetailsResponse = bookmarkService.deleteBookmark(deleteBookmarkRequests);
		//then
		assertEquals(accommodationIdForDeleteTest, bookmarkDetailsResponse.getAccommodationId());
		assertEquals(existUserId, bookmarkDetailsResponse.getUserId());
		assertTrue(bookmarkDetailsResponse.isState());
	}

	@Test
	@DisplayName("Delete bookmark - user does not exist fail")
	void deletedBookmarkUserDoesNotExistFail() {
		//given
		DeleteBookmarkRequests deleteBookmarkRequests = new DeleteBookmarkRequests(noteExistAccommodationId,
			notExistUserId);

		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			bookmarkService.deleteBookmark(deleteBookmarkRequests);
		});
	}

	@Test
	@DisplayName("Delete bookmark - accommodation does not exist fail")
	void deletedBookmarkAccommodationDoesNotExistFail() {
		//given
		DeleteBookmarkRequests deleteBookmarkRequests = new DeleteBookmarkRequests(noteExistAccommodationId,
			notExistUserId);

		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			bookmarkService.deleteBookmark(deleteBookmarkRequests);
		});
	}
}
