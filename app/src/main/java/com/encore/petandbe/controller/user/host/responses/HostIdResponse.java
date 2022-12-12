package com.encore.petandbe.controller.user.host.responses;

import lombok.Getter;

@Getter
public class HostIdResponse {

	private Long hostId;

	public HostIdResponse(Long hostId) {
		this.hostId = hostId;
	}
}
