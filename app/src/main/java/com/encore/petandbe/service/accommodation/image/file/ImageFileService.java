package com.encore.petandbe.service.accommodation.image.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.encore.petandbe.controller.accommodation.image.file.responses.ImageFileListCreateResponse;
import com.encore.petandbe.model.accommodation.image.file.ImageFile;
import com.encore.petandbe.repository.ImageFileRepository;
import com.encore.petandbe.utils.validator.MultipartFileValidator;

@Service
public class ImageFileService {

	private final ImageFileRepository imageFileRepository;

	private final AmazonS3 amazonS3;

	public ImageFileService(ImageFileRepository imageFileRepository, AmazonS3 amazonS3) {
		this.imageFileRepository = imageFileRepository;
		this.amazonS3 = amazonS3;
	}

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.s3.dir}")
	private String dir;

	public ImageFileListCreateResponse storeAndSaveImageFiles(MultipartFile[] files) throws
		IOException {
		if (files.length == 0)
			throw new IllegalArgumentException("Files for upload are not selected");

		int imageNumber = 1;

		List<String> imageFileUrlList = new ArrayList<>();

		for (MultipartFile file : files) {
			String contentType = MultipartFileValidator.of().checkValidationAndGetContentType(file);

			String newFileName = MultipartFileValidator.of()
				.createFileName(imageNumber, contentType);

			ImageFile savedImageFile = putImageToS3AndGetImageFile(file, newFileName);

			imageFileUrlList.add(savedImageFile.getUrl());

			imageNumber++;
		}
		return new ImageFileListCreateResponse(imageFileUrlList);
	}

	private ImageFile putImageToS3AndGetImageFile(MultipartFile file, String newFileName) throws IOException {
		ObjectMetadata objMeta = new ObjectMetadata();

		objMeta.setContentLength(file.getInputStream().available());

		objMeta.setContentType(file.getContentType());

		amazonS3.putObject(bucket + dir, newFileName, file.getInputStream(), objMeta);

		String fileUrl = amazonS3.getUrl(bucket + dir, newFileName).toString();

		ImageFile newImageFile = ImageFile.builder().url(fileUrl).build();

		return imageFileRepository.save(newImageFile);
	}
}
