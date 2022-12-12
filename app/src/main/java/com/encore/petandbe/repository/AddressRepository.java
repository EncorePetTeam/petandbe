package com.encore.petandbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.encore.petandbe.model.accommodation.address.Address;

public interface AddressRepository extends JpaRepository<Address, String>, QuerydslPredicateExecutor<Address> {
}
