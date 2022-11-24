package com.encore.petandbe.service.accommodation.accomodation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.accommodation.responses.AccommodationRetrievalResponse;
import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationUpdatingRequest;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.address.Address;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.repository.AddressRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.AccommodationMapper;
import com.encore.petandbe.utils.validator.TimeValidator;

@Service
@Transactional
public class AccommodationService {

	private final AccommodationRepository accommodationRepository;
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;

	public AccommodationService(AccommodationRepository accommodationRepository,
		AddressRepository addressRepository, UserRepository userRepository) {
		this.accommodationRepository = accommodationRepository;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
	}

	public Long createAccommodation(AccommodationRegistrationRequest accommodationRegistrationRequest) {
		Accommodation savedAccommodation = accommodationRepository.save(
			createAccommodationInstance(accommodationRegistrationRequest));
		return savedAccommodation.getId();
	}

	private Accommodation createAccommodationInstance(
		AccommodationRegistrationRequest request) {
		Address foundAddress = addressRepository.findById(request.getAddressCode())
			.orElseThrow(() -> new NonExistResourceException("Address could not be found"));

		User user = userRepository.findById(request.getUserId())
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));

		TimeValidator.validateTime(request.getWorkingHours());
		TimeValidator.validateTime(request.getWeekendWorkingHours());

		return AccommodationMapper.convertRegistrationRequestToAccommodation(request, user,
			foundAddress);
	}

	public Long updateAccommodation(AccommodationUpdatingRequest request, Long accommodationId) {

		Accommodation accommodation = accommodationRepository.findById(accommodationId)
			.orElseThrow(() -> new NonExistResourceException("Accommodation could not be found"));

		Address address = addressRepository.findById(request.getAddressCode())
			.orElseThrow(() -> new NonExistResourceException("Address could not be found"));

		TimeValidator.validateTime(request.getWorkingHours());
		TimeValidator.validateTime(request.getWeekendWorkingHours());

		accommodation.updateAccommodation(
			AccommodationMapper.convertUpdatingRequestToUpdatingDTO(request, address));

		return accommodationId;
	}

	public Long deleteAccommodationByStatus(Long accommodationId) {
		Accommodation accommodation = accommodationRepository.findById(accommodationId)
			.orElseThrow(() -> new NonExistResourceException("Accommodation could not be found"));

		accommodation.deleteAccommodation();

		return accommodationId;
	}

	public AccommodationRetrievalResponse findAccommodationById(Long accommodationId) {
		Accommodation accommodation = accommodationRepository.findById(accommodationId)
			.orElseThrow(() -> new NonExistResourceException("Accommodation could not be found"));

		return AccommodationMapper.convertAccommodationToRetrievalResponse(accommodation);
	}
}
