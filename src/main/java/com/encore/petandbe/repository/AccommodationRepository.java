package com.encore.petandbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.encore.petandbe.model.accommodation.accommodation.Accommodation;

public interface AccommodationRepository
	extends JpaRepository<Accommodation, Long>, QuerydslPredicateExecutor<Accommodation> {
}
