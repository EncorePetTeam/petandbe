package com.encore.petandbe.service.accommodation.bookmark;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.encore.petandbe.controller.accommodation.bookmark.requests.BookmarkRegistrationRequests;
import com.encore.petandbe.controller.accommodation.bookmark.requests.DeleteBookmarkRequests;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkDetailsResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.bookmark.Bookmark;
import com.encore.petandbe.model.accommodation.bookmark.BookmarkId;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.repository.BookmarkRepository;
import com.encore.petandbe.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class BookmarkServiceTest {

	@InjectMocks
	private BookmarkService bookmarkService;

	@Mock
	private AccommodationRepository accommodationRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private BookmarkRepository bookmarkRepository;

	Long userId = 5L;
	Long accommodationId = 4L;
	User user = User.builder().id(userId).build();
	Accommodation accommodation = Accommodation.builder().id(accommodationId).build();
	BookmarkId bookmarkId = BookmarkId.builder().user(user).accommodation(accommodation).build();
	Bookmark bookmark = Bookmark.builder()
		.bookmarkId(bookmarkId)
		.state(false)
		.build();

	@Test
	@DisplayName("Register bookmark - success")
	void registerBookmarkSuccess() {
		//given
		BookmarkRegistrationRequests bookmarkRegistrationRequests = new BookmarkRegistrationRequests(accommodationId,
			userId);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(accommodationRepository.findById(accommodationId)).willReturn(Optional.ofNullable(accommodation));
		given(bookmarkRepository.save(any(Bookmark.class))).willReturn(bookmark);
		//when
		BookmarkDetailsResponse bookmarkDetailsResponse = bookmarkService.registerBookmark(
			bookmarkRegistrationRequests);
		//then
		assertEquals(userId, bookmarkDetailsResponse.getUserId());
		assertEquals(accommodationId, bookmarkDetailsResponse.getAccommodationId());
	}

	@Test
	@DisplayName("Register bookmark - user does not exist fail")
	void registerBookmarkUserDoesNotExistFail() {
		//given
		BookmarkRegistrationRequests bookmarkRegistrationRequests = new BookmarkRegistrationRequests(accommodationId,
			userId);

		given(userRepository.findById(userId)).willReturn(Optional.empty());
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
		BookmarkRegistrationRequests bookmarkRegistrationRequests = new BookmarkRegistrationRequests(accommodationId,
			userId);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(accommodationRepository.findById(accommodationId)).willReturn(Optional.empty());
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
		DeleteBookmarkRequests deleteBookmarkRequests = new DeleteBookmarkRequests(accommodationId, userId);
		Bookmark bookmark = Bookmark.builder().bookmarkId(bookmarkId).state(true).build();

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(accommodationRepository.findById(accommodationId)).willReturn(Optional.ofNullable(accommodation));
		given(bookmarkRepository.findById(any(BookmarkId.class))).willReturn(Optional.ofNullable(bookmark));
		//when
		BookmarkDetailsResponse bookmarkDetailsResponse = bookmarkService.deleteBookmark(deleteBookmarkRequests);
		//then
		assertEquals(accommodationId, bookmarkDetailsResponse.getAccommodationId());
		assertEquals(userId, bookmarkDetailsResponse.getUserId());
		assertTrue(bookmarkDetailsResponse.isState());
	}

	@Test
	@DisplayName("Delete bookmark - user does not exist fail")
	void deletedBookmarkUserDoesNotExistFail() {
		//given
		DeleteBookmarkRequests deleteBookmarkRequests = new DeleteBookmarkRequests(accommodationId, userId);

		given(userRepository.findById(userId)).willReturn(Optional.empty());
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
		DeleteBookmarkRequests deleteBookmarkRequests = new DeleteBookmarkRequests(accommodationId, userId);

		given(userRepository.findById(userId)).willReturn(Optional.ofNullable(user));
		given(accommodationRepository.findById(accommodationId)).willReturn(Optional.empty());
		//when
		Assertions.assertThrows(NonExistResourceException.class, () -> {
			//then
			bookmarkService.deleteBookmark(deleteBookmarkRequests);
		});
	}
}