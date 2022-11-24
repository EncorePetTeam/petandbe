package com.encore.petandbe.service.accommodation.filtering;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.filtering.requests.FilteringAccommodationRequests;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationListResponse;
import com.encore.petandbe.controller.accommodation.filtering.responses.FilteringAccommodationResponse;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.accommodation.QAccommodation;
import com.encore.petandbe.model.accommodation.room.QRoom;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.repository.AddressRepository;
import com.encore.petandbe.repository.RoomRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class FilteringService {

	private AccommodationRepository accommodationRepository;
	private AddressRepository addressRepository;
	private RoomRepository roomRepository;

	public FilteringService(AccommodationRepository accommodationRepository, AddressRepository addressRepository,
		RoomRepository roomRepository) {
		this.accommodationRepository = accommodationRepository;
		this.addressRepository = addressRepository;
		this.roomRepository = roomRepository;
	}

	public FilteringAccommodationListResponse filteringAccommodation(
		FilteringAccommodationRequests filteringAccommodationRequests) {
		QAccommodation qAccommodation = QAccommodation.accommodation;

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		PageRequest pageRequest = getPageRequest(filteringAccommodationRequests.getPage(),
			filteringAccommodationRequests.getSortCategory());

		List<Long> accommodationIdList = null;

		if (filteringAccommodationRequests.getAddress() != null && !filteringAccommodationRequests.getAddress()
			.equals("")) {
			booleanBuilder.and(qAccommodation.address.addressCode.eq(filteringAccommodationRequests.getAddress()));
		}

		if (filteringAccommodationRequests.getPetCategory() != null
			&& !filteringAccommodationRequests.getPetCategory().equals("")) {
			accommodationIdList = roomRepository.findByPetCategory(
				filteringAccommodationRequests.getPetCategory());
		}

		if (filteringAccommodationRequests.getWeight() != null && !filteringAccommodationRequests.getWeight()
			.equals("")) {
			if (accommodationIdList == null) {
				accommodationIdList = filteringByWeight(filteringAccommodationRequests.getWeight());
			} else {
				List<Long> accommodationFilterdList = filteringByWeight(filteringAccommodationRequests.getWeight());

				accommodationIdList.retainAll(accommodationFilterdList);
			}
		}

		if (filteringAccommodationRequests.getCheckIn() != null && !filteringAccommodationRequests.getCheckIn()
			.equals("")) {
			LocalTime localTime = parseStringToTime(filteringAccommodationRequests.getCheckIn());
			if (checkWeekend(filteringAccommodationRequests.getCheckIn())) {
				booleanBuilder.and(qAccommodation.weekendWorkingStart.before(localTime))
					.and(qAccommodation.weekendWorkingEnd.after(localTime));
			} else {
				booleanBuilder.and(qAccommodation.workingStart.before(localTime))
					.and(qAccommodation.workingEnd.after(localTime));
			}
		}

		if (filteringAccommodationRequests.getCheckOut() != null && !filteringAccommodationRequests.getCheckIn()
			.equals("")) {
			LocalTime localTime = parseStringToTime(filteringAccommodationRequests.getCheckOut());
			if (checkWeekend(filteringAccommodationRequests.getCheckIn())) {
				booleanBuilder.and(qAccommodation.weekendWorkingStart.loe(localTime))
					.and(qAccommodation.weekendWorkingEnd.goe(localTime));
			} else {
				booleanBuilder.and(qAccommodation.workingStart.loe(localTime))
					.and(qAccommodation.workingEnd.goe(localTime));
			}
		}

		if (accommodationIdList != null) {
			booleanBuilder.and(qAccommodation.id.in(accommodationIdList));
		}

		Page<Accommodation> result = accommodationRepository.findAll(booleanBuilder, pageRequest);

		List<FilteringAccommodationResponse> resultList = result.stream()
			.map(e -> new FilteringAccommodationResponse(e.getId(), e.getAccommodationName(),
				e.getAddress().getAddressCode(), e.getAverageRate()))
			.collect(Collectors.toList());

		return new FilteringAccommodationListResponse(resultList);
	}

	private PageRequest getPageRequest(Integer page, String sortCategory) {
		Sort sort;

		if (page == null) {
			page = 1;
		}

		if (sortCategory != null) {
			if (sortCategory.equals("최신 등록 순")) {
				sort = Sort.by("id").descending();
			} else if (sortCategory.equals("북마크 많은 순")) {
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

		BooleanBuilder booleanBuild = new BooleanBuilder();

		if (Double.parseDouble(weight) <= 5) {
			booleanBuild.and(qRoom.weight.loe("5"));
		} else {
			booleanBuild.and(qRoom.weight.gt("5"));
		}

		booleanBuild.and(qRoom.state.eq(false));

		List<Long> result = new ArrayList<>();

		roomRepository.findAll(booleanBuild).forEach(e -> result.add(e.getAccommodation().getId()));

		return result;
	}

	private boolean checkWeekend(String dateTime) {
		LocalDateTime date = LocalDateTime.parse(dateTime,
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		DayOfWeek dayOfWeek = date.getDayOfWeek();

		int dayOfWeekNumber = dayOfWeek.getValue();

		if (dayOfWeekNumber > 5) {
			return true;
		} else {
			return false;
		}
	}

	private LocalTime parseStringToTime(String dateTime) {
		return LocalTime.parse(dateTime.substring(dateTime.length() - 8),
			DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
}
