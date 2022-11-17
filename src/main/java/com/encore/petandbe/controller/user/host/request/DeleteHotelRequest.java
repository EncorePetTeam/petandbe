package com.encore.petandbe.controller.user.host.request;

import lombok.Getter;

@Getter
public final class DeleteHotelRequest {
	private Long hotelId;
	private String state;

	public DeleteHotelRequest() {
	}

	public DeleteHotelRequest(Long hotelId,String state) {
		this.hotelId = hotelId;
		this.state = state;
	}
}
