package com.encore.petandbe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.petandbe.model.user.host.Host;

public interface HostRepository extends JpaRepository<Host, Long> {
	Optional<Host> findByRegistrationNumber(String registrationNumber);
}
