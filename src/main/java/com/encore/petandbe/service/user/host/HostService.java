package com.encore.petandbe.service.user.host;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.user.host.requests.HostRegistrationRequest;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.exception.WrongRequestException;
import com.encore.petandbe.infrastructure.BusinessAuthenticity;
import com.encore.petandbe.model.user.host.Host;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.HostRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.HostMapper;

@Service
public class HostService {

	private final UserRepository userRepository;
	private final HostRepository hostRepository;
	private final BusinessAuthenticity businessAuthenticity;

	public HostService(UserRepository userRepository, HostRepository hostRepository,
		BusinessAuthenticity businessAuthenticity) {
		this.userRepository = userRepository;
		this.hostRepository = hostRepository;
		this.businessAuthenticity = businessAuthenticity;
	}

	@Transactional
	public Long createHost(HostRegistrationRequest hostRegistrationRequest, Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));

		if (!businessAuthenticity.checkAuthenticity(hostRegistrationRequest)) {
			throw new WrongRequestException("Business Inconsistency");
		}

		Optional<Host> optionalHost = hostRepository.findByRegistrationNumber(
			hostRegistrationRequest.getRegistrationNumber());

		Host host;
		if (optionalHost.isPresent()) {
			host = optionalHost.get();
			host.updateHostInfo(hostRegistrationRequest, false);
		} else {
			host = hostRepository.save(HostMapper.of().registHostRequestsToEntity(hostRegistrationRequest));
		}

		user.becomeHost(host);

		return host.getId();
	}
}