package com.encore.petandbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.petandbe.model.accommodation.accommodation.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
