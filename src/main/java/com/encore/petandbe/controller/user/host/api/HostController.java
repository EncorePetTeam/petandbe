package com.encore.petandbe.controller.user.host.api;

import com.encore.petandbe.config.Permission;
import com.encore.petandbe.model.user.user.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.user.host.requests.HostRegistrationRequest;
import com.encore.petandbe.controller.user.host.responses.HostIdResponse;
import com.encore.petandbe.service.user.host.HostService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/host")
public class HostController {

	private final HostService hostService;

	public HostController(HostService hostService) {
		this.hostService = hostService;
	}

	@PostMapping
	@Permission(role = Role.USER)
	public ResponseEntity<HostIdResponse> registerHost(@RequestBody HostRegistrationRequest request, HttpServletRequest httpServletRequest) {
		Integer userId = (Integer) httpServletRequest.getAttribute(Role.USER.getValue());

		return ResponseEntity.status(HttpStatus.CREATED).body(new HostIdResponse(hostService.createHost(request, Long.valueOf(userId))));
	}
}
