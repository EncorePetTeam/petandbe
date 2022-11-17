package com.encore.petandbe.controller.user.host.response;

import java.util.Date;

import lombok.Getter;

@Getter
public final class UpdateHotelResponse {
	private String accommondation_name;
	private String weekday_working_hours;
	private String weekend_working_hours;
	private String hotel_location;
	private String accomoodation_type;
	private String detail;

	public UpdateHotelResponse(String accommondation_name, String weekday_working_hours, String weekend_working_hours,
		String hotel_location, String accomoodation_type, String detail) {
		this.accommondation_name = accommondation_name;
		this.weekday_working_hours = weekday_working_hours;
		this.weekend_working_hours = weekend_working_hours;
		this.hotel_location = hotel_location;
		this.accomoodation_type = accomoodation_type;
		this.detail = detail;
	}
}
