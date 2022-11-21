package com.encore.petandbe.controller.user.host.requests;

import lombok.Getter;

@Getter
public class HostRegistrationRequest {

	private String registrationNumber;
	private String hostName;
	private String openDate;
	private Long userId;

	public HostRegistrationRequest(String registrationNumber, String hostName, String openDate, Long userId) {
		this.registrationNumber = registrationNumber;
		this.hostName = hostName;
		this.openDate = openDate;
		this.userId = userId;
	}
}
