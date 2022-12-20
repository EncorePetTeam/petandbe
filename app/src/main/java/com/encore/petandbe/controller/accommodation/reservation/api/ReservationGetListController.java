package com.encore.petandbe.controller.accommodation.reservation.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationListGetByUserIdResponse;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.reservation.ReservationGetListService;

@RestController
@RequestMapping("/reservation-list")
public class ReservationGetListController {

	private final ReservationGetListService reservationGetListService;

	public ReservationGetListController(ReservationGetListService reservationGetListService) {
		this.reservationGetListService = reservationGetListService;
	}

	@GetMapping
	public ResponseEntity<ReservationListGetByUserIdResponse> getReservationListByUserId(
		HttpServletRequest httpServletRequest,
		@ModelAttribute ReservationListGetByUserIdRequests reservationListGetByUserIdRequests) {
		Integer userId = (Integer)httpServletRequest.getAttribute(Role.USER.getValue());

		ReservationListGetByUserIdRequests reservationListGetByUserIdRequest = new ReservationListGetByUserIdRequests(
			Long.valueOf(userId),
			reservationListGetByUserIdRequests.getPageNum(), reservationListGetByUserIdRequests.getItemAmount());

		return ResponseEntity.ok()
			.body(reservationGetListService.getReservationListByUserId(reservationListGetByUserIdRequest));
	}

}
