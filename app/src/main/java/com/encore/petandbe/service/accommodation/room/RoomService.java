package com.encore.petandbe.service.accommodation.room;

import com.encore.petandbe.controller.accommodation.room.responses.RoomInfoResponse;
import com.encore.petandbe.controller.accommodation.room.responses.RoomRetrievalInfo;
import com.encore.petandbe.model.accommodation.image.file.File;
import com.encore.petandbe.model.accommodation.image.file.ImageFile;
import com.encore.petandbe.repository.FileRepository;
import com.encore.petandbe.repository.ImageFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.room.requests.RoomRegistrationRequest;
import com.encore.petandbe.controller.accommodation.room.requests.RoomUpdateRequest;
import com.encore.petandbe.controller.accommodation.room.responses.RoomRetrievalResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.room.Room;
import com.encore.petandbe.repository.AccommodationRepository;
import com.encore.petandbe.repository.RoomRepository;
import com.encore.petandbe.utils.mapper.RoomMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomService {

	private final RoomRepository roomRepository;
	private final AccommodationRepository accommodationRepository;
	private final FileRepository fileRepository;
	private final ImageFileRepository imageFileRepository;

	public RoomService(RoomRepository roomRepository, AccommodationRepository accommodationRepository, FileRepository fileRepository, ImageFileRepository imageFileRepository) {
		this.roomRepository = roomRepository;
		this.accommodationRepository = accommodationRepository;
		this.fileRepository = fileRepository;
		this.imageFileRepository = imageFileRepository;
	}

	public Long createRoom(RoomRegistrationRequest roomRegistrationRequest) {
		Accommodation accommodation = accommodationRepository.findById(roomRegistrationRequest.getAccommodationId())
			.orElseThrow(() -> new NonExistResourceException("Accommodation could not be found"));

		Room savedRoom = roomRepository.save(
				RoomMapper.of().createRoomRequestToEntity(roomRegistrationRequest, accommodation));

		List<ImageFile> imageFiles = imageFileRepository.findByUrlIn(roomRegistrationRequest.getImageUrlList());

		List<File> files = imageFiles.stream()
				.map(e -> File.builder().room(savedRoom).imageFile(e).build())
				.collect(Collectors.toList());

		fileRepository.saveAll(files);

		return savedRoom.getId();
	}

	public Long updateRoom(RoomUpdateRequest roomUpdateRequest, Long roomId) {
		Room room = roomRepository.findById(roomId)
			.orElseThrow(()-> new NonExistResourceException("Room does not exist"));

		room.updateRoom(RoomMapper.convertUpdatingRequestToUpdatingDTO(roomUpdateRequest));

		return roomId;
	}

	public Long deleteRoomById(Long roomId) {
		Room room = roomRepository.findById(roomId)
			.orElseThrow(() -> new NonExistResourceException("Room could not be found"));

		room.deleteRoom();

		return roomId;
	}

	public RoomRetrievalResponse findRoomById(Long roomId) {
		Room room = roomRepository.findById(roomId)
			.orElseThrow(()-> new NonExistResourceException("Room could not be found"));

		List<File> files = fileRepository.findByRoomId(roomId);

		List<Long> imageFileIdList = files.stream().map(e-> e.getImageFile().getId()).collect(Collectors.toList());

		List<ImageFile> imageFiles = imageFileRepository.findByIdIn(imageFileIdList);

		List<String> imageFileUrlList = imageFiles.stream().map(ImageFile::getUrl).collect(Collectors.toList());

		return RoomMapper.convertRoomToRetrievalResponse(room, imageFileUrlList);
	}

	public RoomInfoResponse findRoomInfoByAccommodationId(Long accommodationId){

		List<Room>  rooms = roomRepository.findByAccommodationId(accommodationId);

		List<RoomRetrievalInfo> roomRetrievalInfos = new ArrayList<>();

		for (Room room : rooms){
			roomRetrievalInfos.add(RoomMapper.convertRoomToRetrievalInfo(room));
		}

		return new RoomInfoResponse(accommodationId, roomRetrievalInfos);
	}

}
