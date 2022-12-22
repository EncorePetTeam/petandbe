package com.encore.petandbe.controller.accommodation.image.file.api;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.encore.petandbe.config.Permission;
import com.encore.petandbe.controller.accommodation.image.file.responses.ImageFileListCreateResponse;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.image.file.ImageFileService;

@RestController
@RequestMapping("/image-file")
public class ImageFileController {

	private final ImageFileService imageFileService;

	public ImageFileController(ImageFileService imageFileService) {
		this.imageFileService = imageFileService;
	}

	@Permission(role = Role.USER)
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ImageFileListCreateResponse> storeImageFiles(
		@Valid @RequestParam("file") MultipartFile[] files) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(imageFileService.storeAndSaveImageFiles(files));
	}
}
