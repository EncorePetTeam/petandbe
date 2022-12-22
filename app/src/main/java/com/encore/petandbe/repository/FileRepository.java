package com.encore.petandbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.petandbe.model.accommodation.image.file.File;

public interface FileRepository extends JpaRepository<File, Long> {
	List<File> findByAccommodationId(Long accommodationId);
}
