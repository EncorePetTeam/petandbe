package com.encore.petandbe.controller.user.host.response;

import lombok.Getter;

@Getter
public final class DeleteHotelResponse {

	private Long hotelId;
	private String state;

	public DeleteHotelResponse(Long hotelId	,String state) {
		this.hotelId = hotelId;
		this.state = state;
	}
}
