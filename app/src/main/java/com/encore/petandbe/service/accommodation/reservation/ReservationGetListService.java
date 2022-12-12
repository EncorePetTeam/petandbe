package com.encore.petandbe.service.accommodation.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.encore.petandbe.controller.accommodation.reservation.requests.ReservationListGetByUserIdRequests;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationListGetByUserIdResponse;
import com.encore.petandbe.controller.accommodation.reservation.responses.ReservationWithRoomResponse;
import com.encore.petandbe.exception.NonExistResourceException;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.ReservationListSearchRepository;
import com.encore.petandbe.repository.UserRepository;
import com.encore.petandbe.utils.mapper.ReservationGetListMapper;

@Service
public class ReservationGetListService {

	private final ReservationListSearchRepository reservationListSearchRepository;
	private final UserRepository userRepository;

	public ReservationGetListService(ReservationListSearchRepository reservationListSearchRepository,
		UserRepository userRepository) {
		this.reservationListSearchRepository = reservationListSearchRepository;
		this.userRepository = userRepository;
	}

	public ReservationListGetByUserIdResponse getReservationListByUserId(
		ReservationListGetByUserIdRequests reservationListGetByUserIdRequests) {
		User user = userRepository.findById(reservationListGetByUserIdRequests.getUserId())
			.orElseThrow(() -> new NonExistResourceException("User could not be found"));

		Page<ReservationWithRoomResponse> reservationPage = reservationListSearchRepository.getReservationListPage(
			user.getId(),
			convertPageRequest(reservationListGetByUserIdRequests));

		return ReservationGetListMapper.of().reservationListToResponse(reservationPage);
	}

	private static PageRequest convertPageRequest(
		ReservationListGetByUserIdRequests reservationListGetByUserIdRequests) {
		return PageRequest.of(reservationListGetByUserIdRequests.getPageNum() - 1,
			reservationListGetByUserIdRequests.getAmount());
	}

}
