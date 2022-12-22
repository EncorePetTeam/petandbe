package com.encore.petandbe.utils.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.exception.WrongRequestException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultipartFileValidator {

	private static MultipartFileValidator multipartFileValidator = null;

	public static MultipartFileValidator of() {
		if (multipartFileValidator == null) {
			multipartFileValidator = new MultipartFileValidator();
		}
		return multipartFileValidator;
	}

	public String checkValidationAndGetContentType(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String contentType = fileName.substring(fileName.lastIndexOf(".") + 1);

		if (file.isEmpty())
			throw new WrongRequestException("File is empty");

		if (fileName.contains(".."))
			throw new WrongRequestException("File name contains invalid path sequence" + fileName);

		if (ObjectUtils.isEmpty(contentType))
			throw new NonExistResourceException("Wrong file is selected: " + fileName + ". Please check the file.");

		if (!contentType.contains("jpeg") && !contentType.contains("jpg") && !contentType.contains("png"))
			throw new WrongRequestException("Please upload jpeg, jpg, png files");
		return contentType;
	}

	public String createFileName(int number, String contentType) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + number + "." + contentType;
	}
}
