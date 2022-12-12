package com.encore.petandbe.infrastructure;

import com.encore.petandbe.controller.user.host.requests.HostRegistrationRequest;

public interface BusinessAuthenticity {
	public boolean checkAuthenticity(HostRegistrationRequest hostRegistrationRequest);
}
