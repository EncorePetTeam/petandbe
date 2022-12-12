package com.encore.petandbe.controller.accommodation.reservation.responses;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReservationListGetByUserIdResponse {

	private final int endPage;
	private final int selectPage;
	private final int amountItem;
	private final List<ReservationWithRoomResponse> reservationWithRoomResponseList;

	public ReservationListGetByUserIdResponse(int endPage, int selectPage, int amountItem,
		List<ReservationWithRoomResponse> reservationWithRoomResponseList) {
		this.endPage = endPage;
		this.selectPage = selectPage;
		this.amountItem = amountItem;
		this.reservationWithRoomResponseList = reservationWithRoomResponseList;
	}
}
