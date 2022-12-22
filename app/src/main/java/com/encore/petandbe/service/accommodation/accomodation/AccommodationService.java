package com.encore.petandbe.service.accommodation.accomodation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationRegistrationRequest;
import com.encore.petandbe.controller.accommodation.accommodation.requests.AccommodationUpdatingRequest;
import com.encore.petandbe.controller.accommodation.accommodation.responses.AccommodationRetrievalResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.address.Address;
import com.encore.petandbe.model.accommodation.image.file.File;
import com.encore.petandbe.model.accommodation.image.file.ImageFile;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.repository.AddressRepository;
import com.encore.petandbe.repository.FileRepository;
import com.encore.petandbe.repository.ImageFileRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.AccommodationMapper;
import com.encore.petandbe.utils.validator.TimeValidator;

@Service
@Transactional
public class AccommodationService {

	private final AccommodationRepository accommodationRepository;
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;
	private final FileRepository fileRepository;
	private final ImageFileRepository imageFileRepository;

	public AccommodationService(AccommodationRepository accommodationRepository, AddressRepository addressRepository,
		UserRepository userRepository, FileRepository fileRepository, ImageFileRepository imageFileRepository) {
		this.accommodationRepository = accommodationRepository;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.fileRepository = fileRepository;
		this.imageFileRepository = imageFileRepository;
	}

	@Transactional
	public Long createAccommodation(AccommodationRegistrationRequest accommodationRegistrationRequest, Long userId) {
		Accommodation savedAccommodation = accommodationRepository.save(
			createAccommodationInstance(accommodationRegistrationRequest, userId));

		List<ImageFile> imageFileList = imageFileRepository.findByUrlIn(
			accommodationRegistrationRequest.getImageUrlList());

		List<File> fileList = imageFileList.stream()
			.map(e -> File.builder().accommodation(savedAccommodation).imageFile(e).build())
			.collect(Collectors.toList());

		fileRepository.saveAll(fileList);

		return savedAccommodation.getId();
	}

	private Accommodation createAccommodationInstance(
		AccommodationRegistrationRequest request, Long userId) {
		Address foundAddress = addressRepository.findById(request.getAddressCode())
			.orElseThrow(() -> new NonExistResourceException("Address could not be found"));

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));

		TimeValidator.validateTime(request.getWorkingHours());
		TimeValidator.validateTime(request.getWeekendWorkingHours());

		return AccommodationMapper.convertRegistrationRequestToAccommodation(request, user,
			foundAddress);
	}

	@Transactional
	public Long updateAccommodation(AccommodationUpdatingRequest request, Long accommodationId) {
		Accommodation accommodation = checkAccommodationExistAndGetAccommodation(accommodationId);

		Address address = addressRepository.findById(request.getAddressCode())
			.orElseThrow(() -> new NonExistResourceException("Address could not be found"));

		TimeValidator.validateTime(request.getWorkingHours());
		TimeValidator.validateTime(request.getWeekendWorkingHours());

		accommodation.updateAccommodation(
			AccommodationMapper.convertUpdatingRequestToUpdatingDTO(request, address));

		return accommodationId;
	}

	@Transactional
	public Long deleteAccommodationByStatus(Long accommodationId) {
		Accommodation accommodation = checkAccommodationExistAndGetAccommodation(accommodationId);

		accommodation.deleteAccommodation();

		return accommodationId;
	}

	public AccommodationRetrievalResponse findAccommodationById(Long accommodationId) {
		Accommodation accommodation = checkAccommodationExistAndGetAccommodation(accommodationId);

		List<File> fileList = fileRepository.findByAccommodationId(accommodationId);

		List<Long> imageFileIdList = fileList.stream().map(e -> e.getImageFile().getId()).collect(Collectors.toList());

		List<ImageFile> imageFileList = imageFileRepository.findByIdIn(imageFileIdList);

		List<String> imageFileUrlList = imageFileList.stream().map(ImageFile::getUrl).collect(Collectors.toList());

		return AccommodationMapper.convertAccommodationToRetrievalResponse(accommodation, imageFileUrlList);
	}

	private Accommodation checkAccommodationExistAndGetAccommodation(Long accommodationId) {
		return accommodationRepository.findById(accommodationId)
			.orElseThrow(() -> new NonExistResourceException("Accommodation could not be found"));
	}
}
