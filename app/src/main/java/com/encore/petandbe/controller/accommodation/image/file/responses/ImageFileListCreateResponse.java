package com.encore.petandbe.controller.accommodation.image.file.responses;

import java.util.List;

import lombok.Getter;

@Getter
public class ImageFileListCreateResponse {

	private final List<String> imageUrlList;

	public ImageFileListCreateResponse(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}
}
