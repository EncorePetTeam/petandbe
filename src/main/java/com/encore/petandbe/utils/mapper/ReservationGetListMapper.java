package com.encore.petandbe.utils.mapper;

import org.springframework.data.domain.Page;

import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationListGetByUserIdResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationWithRoomResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationGetListMapper {

	private static ReservationGetListMapper reservationGetListMapper = null;

	public static ReservationGetListMapper of() {
		if(reservationGetListMapper == null){
			reservationGetListMapper = new ReservationGetListMapper();
		}
		return reservationGetListMapper;
	}

	public ReservationListGetByUserIdResponse reservationListToResponse(Page<ReservationWithRoomResponse> reservationPage){
		return new ReservationListGetByUserIdResponse(reservationPage.getTotalPages(), reservationPage.getNumber() + 1,
			reservationPage.getNumberOfElements(), reservationPage.getContent());
	}
}
