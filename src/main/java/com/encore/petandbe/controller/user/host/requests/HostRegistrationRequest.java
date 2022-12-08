package com.encore.petandbe.controller.user.host.requests;

import lombok.Getter;

@Getter
public class HostRegistrationRequest {

	private final String registrationNumber;
	private final String hostName;
	private final String openDate;

	public HostRegistrationRequest(String registrationNumber, String hostName, String openDate) {
		this.registrationNumber = registrationNumber;
		this.hostName = hostName;
		this.openDate = openDate;
	}
}
