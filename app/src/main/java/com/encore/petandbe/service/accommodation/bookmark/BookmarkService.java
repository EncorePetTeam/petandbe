package com.encore.petandbe.service.accommodation.bookmark;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.bookmark.requests.BookmarkRegistrationRequests;
import com.encore.petandbe.controller.accommodation.bookmark.requests.DeleteBookmarkRequests;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkAccommodationListResponse;
import com.encore.petandbe.controller.accommodation.bookmark.responses.BookmarkDetailsResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.bookmark.Bookmark;
import com.encore.petandbe.model.accommodation.bookmark.BookmarkId;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.repository.BookmarkRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.BookmarkMapper;

@Service
public class BookmarkService {

	private final BookmarkRepository bookmarkRepository;
	private final UserRepository userRepository;
	private final AccommodationRepository accommodationRepository;

	public BookmarkService(BookmarkRepository bookmarkRepository, UserRepository userRepository,
		AccommodationRepository accommodationRepository) {
		this.bookmarkRepository = bookmarkRepository;
		this.userRepository = userRepository;
		this.accommodationRepository = accommodationRepository;
	}

	@Transactional
	public BookmarkDetailsResponse registerBookmark(BookmarkRegistrationRequests bookmarkRegistrationRequests) {
		BookmarkId bookmarkId = checkUserAndAccommodationAndGetBookmarkId(bookmarkRegistrationRequests.getUserId(),
			bookmarkRegistrationRequests.getAccommodationId());

		Bookmark bookmark = BookmarkMapper.of().createBookmarkIfNotExistUpdateBookmarkIfExist(bookmarkId);

		Bookmark savedBookmark = bookmarkRepository.save(bookmark);

		return BookmarkMapper.of().bookmarkToDetailsResponse(savedBookmark);
	}

	@Transactional
	public BookmarkDetailsResponse deleteBookmark(DeleteBookmarkRequests deleteBookmarkRequests) {
		BookmarkId bookmarkId = checkUserAndAccommodationAndGetBookmarkId(deleteBookmarkRequests.getUserId(),
			deleteBookmarkRequests.getAccommodationId());

		Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
			.orElseThrow(() -> new NonExistResourceException("Bookmark could not be found"));

		bookmark.deleteBookmark();

		return BookmarkMapper.of().bookmarkToDetailsResponse(bookmark);
	}

	public BookmarkId checkUserAndAccommodationAndGetBookmarkId(Long userId, Long accommodationId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User could not be fou"));

		Accommodation accommodation = accommodationRepository.findById(accommodationId)
			.orElseThrow(() -> new NonExistResourceException("Accommodation could not be found"));

		return BookmarkMapper.of().requestToBookmarkId(user, accommodation);
	}

	public BookmarkAccommodationListResponse findAccommodationListByBookmark(Long userId) {
		return null;
	}
}
