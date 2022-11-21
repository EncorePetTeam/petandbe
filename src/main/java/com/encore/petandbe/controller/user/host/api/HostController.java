package com.encore.petandbe.controller.user.host.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.user.host.requests.HostRegistrationRequest;
import com.encore.petandbe.controller.user.host.responses.HostIdResponse;
import com.encore.petandbe.service.user.host.HostService;

@RestController
@RequestMapping("/host")
public class HostController {

	private final HostService hostService;

	public HostController(HostService hostService) {
		this.hostService = hostService;
	}

	@PostMapping
	public ResponseEntity<HostIdResponse> registerHost(@RequestBody HostRegistrationRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new HostIdResponse(hostService.createHost(request)));
	}
}
