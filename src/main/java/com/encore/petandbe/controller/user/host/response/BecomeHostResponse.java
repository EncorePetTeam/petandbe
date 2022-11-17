package com.encore.petandbe.controller.user.host.response;

import lombok.Getter;

@Getter
public final class BecomeHostResponse {
	private String business_registation_number;
	private String host_name;
	private String business_open_date;
	private String state;

	public BecomeHostResponse(String business_registation_number,String host_name, String business_open_date,String state) {
		this.business_registation_number = business_registation_number;
		this.host_name = host_name;
		this.business_open_date = business_open_date;
		this.state = state;
	}
}
