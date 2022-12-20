package com.encore.petandbe.service.accommodation.reservation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationListGetByUserIdResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationWithRoomResponse;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.ReservationListSearchRepository;
import com.encore.petandbe.repository.UserRepository;

@SpringBootTest
@Transactional
class ReservationGetListServiceTest {

	@Autowired
	private ReservationGetListService reservationGetListService;

	@Autowired
	private ReservationListSearchRepository reservationListSearchRepository;

	@Autowired
	private UserRepository userRepository;

	private final Long userId = 5L;
	private final int pageNum = 1;
	private final int itemAmount = 7;
	private final LocalDateTime checkInDate = LocalDateTime.parse("2022-05-21T14:00:00");
	private final LocalDateTime checkOutDate = LocalDateTime.parse("2022-05-23T16:00:00");
	private final Integer amount = 100000;

	@Test
	@DisplayName("Get Reservation List Page - Success")
	void getReservationListByUserId() {
		//given
		User user = User.builder().id(userId).build();

		List<ReservationWithRoomResponse> reservationWithRoomResponseList = new ArrayList<>();
		for (int i = 0; i < itemAmount; i++) {
			reservationWithRoomResponseList.add(new ReservationWithRoomResponse((long)i, (long)i,
				checkInDate, checkOutDate, amount));

			ReservationListGetByUserIdRequests reservationListGetByUserIdRequests =
				new ReservationListGetByUserIdRequests(userId, pageNum, itemAmount);

			Page<ReservationWithRoomResponse> reservationWithRoomResponses = new PageImpl<>(
				reservationWithRoomResponseList,
				PageRequest.of(pageNum - 1, itemAmount), reservationWithRoomResponseList.size());
			//when
			ReservationListGetByUserIdResponse reservationListByUserId =
				reservationGetListService.getReservationListByUserId(reservationListGetByUserIdRequests);
			//then
			assertEquals(pageNum, reservationListByUserId.getSelectPage());
			assertEquals(itemAmount, reservationListByUserId.getReservationWithRoomResponseList().size());
		}
	}
}