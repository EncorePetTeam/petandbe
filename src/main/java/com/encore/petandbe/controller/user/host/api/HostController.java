package com.encore.petandbe.controller.user.host.api;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.user.host.request.BecomeHostRequest;
import com.encore.petandbe.controller.user.host.request.RegistHotelRequest;
import com.encore.petandbe.controller.user.host.response.BecomeHostResponse;
import com.encore.petandbe.controller.user.host.response.RegistHotelResponse;
import com.encore.petandbe.service.user.host.HostService;

@RestController
@RequestMapping("/host")
public class HostController {
	private final HostService hostService;

	public HostController(HostService hostService) {
		this.hostService = hostService;
	}

	@PostMapping("/become")
	public ResponseEntity<BecomeHostResponse> becomeHost(@RequestBody BecomeHostRequest becomeHostRequest) {
		return ResponseEntity.created(URI.create("/hotel/become"))
			.body(hostService.becomeHost(becomeHostRequest));
	}
}
