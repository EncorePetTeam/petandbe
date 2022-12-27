package com.encore.petandbe.service.accommodation.filtering;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.filtering.requests.FilteringAccommodationRequests;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationListResponse;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.accommodation.QAccommodation;
import com.encore.petandbe.model.accommodation.bookmark.Bookmark;
import com.encore.petandbe.model.accommodation.filtering.category.SortCategory;
import com.encore.petandbe.model.accommodation.image.file.File;
import com.encore.petandbe.model.accommodation.image.file.ImageFile;
import com.encore.petandbe.model.accommodation.room.QRoom;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.repository.BookmarkRepository;
import com.encore.petandbe.repository.FileRepository;
import com.encore.petandbe.repository.ImageFileRepository;
import com.encore.petandbe.repository.RoomRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.BookmarkMapper;
import com.querydsl.core.BooleanBuilder;

@Service
public class FilteringService {

	private final AccommodationRepository accommodationRepository;
	private final RoomRepository roomRepository;
	private final BookmarkRepository bookmarkRepository;
	private final UserRepository userRepository;
	private final FileRepository fileRepository;
	private final ImageFileRepository imageFileRepository;
	private BooleanBuilder booleanBuilder;
	private List<Long> accommodationIdList;
	private static final QAccommodation qAccommodation = QAccommodation.accommodation;

	public FilteringService(AccommodationRepository accommodationRepository, RoomRepository roomRepository,
		BookmarkRepository bookmarkRepository, UserRepository userRepository, FileRepository fileRepository,
		ImageFileRepository imageFileRepository) {
		this.accommodationRepository = accommodationRepository;
		this.roomRepository = roomRepository;
		this.bookmarkRepository = bookmarkRepository;
		this.userRepository = userRepository;
		this.fileRepository = fileRepository;
		this.imageFileRepository = imageFileRepository;
	}

	public FilteringAccommodationListResponse filteringAccommodation(Long userId,
		FilteringAccommodationRequests filteringAccommodationRequests) {

		accommodationIdList = null;
		booleanBuilder = new BooleanBuilder();

		PageRequest pageRequest = getPageRequest(filteringAccommodationRequests.getPage(),
			filteringAccommodationRequests.getSortCategory());

		filterByAddressIfRequirementsExist(filteringAccommodationRequests.getAddress());

		getAccommodationIdListByPetCategoryIfRequirementsExist(filteringAccommodationRequests.getPetCategory());

		getAccommodationIdListFilteredByWeightIfRequirementsExist(filteringAccommodationRequests.getWeight());

		filterByCheckInOutTimeIfRequirementsExist(filteringAccommodationRequests.getCheckIn());

		filterByCheckInOutTimeIfRequirementsExist(filteringAccommodationRequests.getCheckOut());

		filterByAccommodationIdListIfPetCategoryOrWeightRequirementsExist();

		Page<Accommodation> result = accommodationRepository.findAll(booleanBuilder, pageRequest);

		List<FilteringAccommodationResponse> resultList = getFilteringAccommodationResponses(userId, result);

		return new FilteringAccommodationListResponse(resultList);
	}

	private List<FilteringAccommodationResponse> getFilteringAccommodationResponses(Long userId,
		Page<Accommodation> result) {
		if (userId == null) {
			return result.stream()
				.map(e -> {
					String imageFileUrl = getImageFileUrl(e);

					return new FilteringAccommodationResponse(e.getId(), e.getAccommodationName(),
						e.getAddress().getAddressCode(), e.getLocation(), e.getLotNumber(), e.getAverageRate(), false,
						imageFileUrl);
				})
				.collect(Collectors.toList());
		} else {
			User user = userRepository.findById(userId)
				.orElseThrow(() -> new NonExistResourceException("User does not exist"));

			return result.stream().map(e -> {
				Optional<Bookmark> bookmark = bookmarkRepository.findById(
					BookmarkMapper.of().requestToBookmarkId(user, e));
				boolean isBookmarked = bookmark.isPresent();
				if (isBookmarked)
					isBookmarked = !bookmark.get().getState();

				String imageFileUrl = getImageFileUrl(e);

				return new FilteringAccommodationResponse(e.getId(), e.getAccommodationName(),
					e.getAddress().getAddressCode(), e.getLocation(), e.getLotNumber(), e.getAverageRate(),
					isBookmarked, imageFileUrl);
			}).collect(Collectors.toList());
		}
	}

	private String getImageFileUrl(Accommodation accommodation) {
		List<File> files = fileRepository.findByAccommodationId(accommodation.getId());

		ImageFile imageFile = imageFileRepository.findById(files.get(0).getImageFile().getId())
			.orElseThrow(() -> new NonExistResourceException("ImageFile does not exist"));
		return imageFile.getUrl();
	}

	private void filterByAccommodationIdListIfPetCategoryOrWeightRequirementsExist() {
		if (!checkAccommodationIdListIsNull()) {
			booleanBuilder.and(qAccommodation.id.in(accommodationIdList));
		}
	}

	private void filterByCheckInOutTimeIfRequirementsExist(String time) {
		if (isRequirementExist(time)) {
			LocalTime localTime = parseStringToTime(time);
			if (checkWeekend(time)) {
				booleanBuilder.and(qAccommodation.weekendWorkingStart.loe(localTime))
					.and(qAccommodation.weekendWorkingEnd.goe(localTime));
			} else {
				booleanBuilder.and(qAccommodation.workingStart.loe(localTime))
					.and(qAccommodation.workingEnd.goe(localTime));
			}
		}
	}

	private void getAccommodationIdListFilteredByWeightIfRequirementsExist(String weight) {
		if (isRequirementExist(weight)) {
			if (checkAccommodationIdListIsNull()) {
				accommodationIdList = filteringByWeight(weight);
			} else {
				List<Long> accommodationFilteredList = filteringByWeight(weight);
				accommodationIdList.retainAll(accommodationFilteredList);
			}
		}
	}

	private void getAccommodationIdListByPetCategoryIfRequirementsExist(String petCategory) {
		if (isRequirementExist(petCategory)) {
			if (checkAccommodationIdListIsNull()) {
				accommodationIdList = roomRepository.findByPetCategory(
					petCategory);
			} else {
				List<Long> accommodationFilteredList = roomRepository.findByPetCategory(petCategory);
				accommodationIdList.retainAll(accommodationFilteredList);
			}
		}
	}

	private void filterByAddressIfRequirementsExist(String address) {
		if (isRequirementExist(address)) {
			booleanBuilder.and(qAccommodation.address.addressCode.eq(address));
		}
	}

	private PageRequest getPageRequest(Integer page, SortCategory sortCategory) {
		Sort sort;

		if (page == null) {
			page = 1;
		}

		if (sortCategory != null) {
			if (sortCategory.equals(SortCategory.NEWEST)) {
				sort = Sort.by("id").descending();
			} else if (sortCategory.equals(SortCategory.BOOKMARK)) {
				sort = Sort.by("bookmarkCount").descending();
			} else {
				sort = Sort.by("averageRate").descending();
			}
		} else {
			sort = Sort.by("averageRate").descending();
		}

		return PageRequest.of(page - 1, 10, sort);
	}

	private List<Long> filteringByWeight(String weight) {
		QRoom qRoom = QRoom.room;

		BooleanBuilder roomBooleanBuild = new BooleanBuilder();

		if (Double.parseDouble(weight) <= 5) {
			roomBooleanBuild.and(qRoom.weight.loe("5"));
		} else {
			roomBooleanBuild.and(qRoom.weight.gt("5"));
		}

		roomBooleanBuild.and(qRoom.state.eq(false));

		List<Long> result = new ArrayList<>();

		roomRepository.findAll(roomBooleanBuild).forEach(e -> result.add(e.getAccommodation().getId()));

		return result;
	}

	private boolean checkWeekend(String dateTime) {
		LocalDateTime date = LocalDateTime.parse(dateTime,
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		DayOfWeek dayOfWeek = date.getDayOfWeek();

		int dayOfWeekNumber = dayOfWeek.getValue();

		return dayOfWeekNumber > 5;
	}

	private LocalTime parseStringToTime(String dateTime) {
		return LocalTime.parse(dateTime.substring(dateTime.length() - 8),
			DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	private boolean isRequirementExist(String requirement) {
		return requirement != null && !requirement.equals("");
	}

	private boolean checkAccommodationIdListIsNull() {
		return accommodationIdList == null;
	}
}