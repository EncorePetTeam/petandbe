package com.encore.petandbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.encore.petandbe.model.accommodation.room.Room;

public interface RoomRepository extends JpaRepository<Room, Long>, QuerydslPredicateExecutor<Room> {
	@Query(nativeQuery = true, value = "SELECT DISTINCT r.accommodation_id FROM room r WHERE r.pet_category = :petCategory and r.state = false")
	List<Long> findByPetCategory(@Param("petCategory") String petCategory);
}
