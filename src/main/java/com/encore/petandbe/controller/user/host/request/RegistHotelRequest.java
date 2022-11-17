package com.encore.petandbe.controller.user.host.request;

import lombok.Getter;

@Getter
public final  class RegistHotelRequest {
	private String accommondation_name;
	private String weekday_working_hours;
	private String weekend_working_hours;
	private String hotel_location;
	private String accomoodation_type;
	private String detail;

	public RegistHotelRequest() {
	}

	public RegistHotelRequest(String accommondation_name, String weekday_working_hours, String weekend_working_hours,
		String hotel_location, String accomoodation_type, String detail) {
		this.accommondation_name = accommondation_name;
		this.weekday_working_hours = weekday_working_hours;
		this.weekend_working_hours = weekend_working_hours;
		this.hotel_location = hotel_location;
		this.accomoodation_type = accomoodation_type;
		this.detail = detail;
	}
}
