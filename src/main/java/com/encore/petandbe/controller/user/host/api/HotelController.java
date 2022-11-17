package com.encore.petandbe.controller.user.host.api;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.user.host.request.DeleteHotelRequest;
import com.encore.petandbe.controller.user.host.request.RegistHotelRequest;
import com.encore.petandbe.controller.user.host.request.UpdateHotelRequest;
import com.encore.petandbe.controller.user.host.response.DeleteHotelResponse;
import com.encore.petandbe.controller.user.host.response.RegistHotelResponse;
import com.encore.petandbe.controller.user.host.response.UpdateHotelResponse;
import com.encore.petandbe.service.accommodation.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	private final HotelService hotelService;

	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	@PostMapping("/regist")
	public ResponseEntity<RegistHotelResponse> registHotel(@RequestBody RegistHotelRequest registHotelRequest) {
		return ResponseEntity.created(URI.create("/hotel/regist"))
			.body(hotelService.registHotel(registHotelRequest));
	}

	@PostMapping("/delete")
	public ResponseEntity<DeleteHotelResponse> deleteHotel(@RequestBody DeleteHotelRequest deleteHotelRequest) {
		return ResponseEntity.ok().body(hotelService.deleteHotel(deleteHotelRequest));
	}

	@PostMapping("/update")
	public ResponseEntity<UpdateHotelResponse> updateHotel(@RequestBody UpdateHotelRequest updateHotelRequest) {
		return ResponseEntity.ok().body(hotelService.updateHotel(updateHotelRequest));
	}
}
