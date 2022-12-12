package com.encore.petandbe.utils.mapper;

import com.encore.petandbe.controller.user.host.requests.HostRegistrationRequest;
import com.encore.petandbe.model.user.host.Host;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HostMapper {

	private static HostMapper hostMapper;

	public static HostMapper of() {
		if (hostMapper == null) {
			hostMapper = new HostMapper();
		}
		return hostMapper;
	}

	public Host registHostRequestsToEntity(HostRegistrationRequest hostRegistrationRequest) {
		return Host.builder()
			.hostName(hostRegistrationRequest.getHostName())
			.registrationNumber(hostRegistrationRequest.getRegistrationNumber())
			.openDate(hostRegistrationRequest.getOpenDate())
			.build();
	}
}
