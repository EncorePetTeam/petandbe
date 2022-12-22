package com.encore.petandbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.petandbe.model.accommodation.image.file.ImageFile;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
	List<ImageFile> findByIdIn(List<Long> imageFileIdList);

	List<ImageFile> findByUrlIn(List<String> imageUrlList);
}
