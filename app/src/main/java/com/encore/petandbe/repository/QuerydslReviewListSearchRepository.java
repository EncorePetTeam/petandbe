package com.encore.petandbe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.petandbe.controller.accommodation.review.responses.ReviewDetailsResponse;
import com.encore.petandbe.controller.accommodation.review.responses.ReviewWithAccommodationResponse;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.accommodation.QAccommodation;
import com.encore.petandbe.model.accommodation.reservation.QReservation;
import com.encore.petandbe.model.accommodation.review.QReview;
import com.encore.petandbe.model.accommodation.review.Review;
import com.encore.petandbe.model.accommodation.room.QRoom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class QuerydslReviewListSearchRepository extends QuerydslRepositorySupport
	implements ReviewListSearchRepository {

	private QReview qReview = QReview.review;
	private QAccommodation qAccommodation = QAccommodation.accommodation;
	private QReservation qReservation = QReservation.reservation;
	private QRoom qRoom = QRoom.room;

	public QuerydslReviewListSearchRepository() {
		super(Review.class);
	}

	@Override
	public Page<ReviewWithAccommodationResponse> getReviewListPageByUserId(Long userId, Pageable pageable) {
		List<ReviewWithAccommodationResponse> reviewWithAccommodationResponseList
			= getReviewWithAccommodationResponseList(userId, pageable);

		Long reviewAmount = countReviewAmountByUser(userId);

		return new PageImpl<>(reviewWithAccommodationResponseList, pageable, reviewAmount);
	}

	private Long countReviewAmountByUser(Long userId) {
		return from(qReview)
			.select(qReview.count())
			.where(qReview.user.id.eq(userId).and(qReview.state.isFalse()))
			.fetchOne();
	}

	private List<ReviewWithAccommodationResponse> getReviewWithAccommodationResponseList(Long userId,
		Pageable pageable) {
		return from(qReview)
			.select(Projections.constructor(ReviewWithAccommodationResponse.class,
				qAccommodation.accommodationName,
				qAccommodation.id,
				qReservation.id, qReview.rate, qReview.content))
			.leftJoin(qReservation)
			.fetchJoin()
			.on(qReservation.id.eq(qReview.reservation.id))
			.leftJoin(qAccommodation)
			.fetchJoin()
			.on(qAccommodation.id.eq(qReservation.room.accommodation.id))
			.where(qReview.user.id.eq(userId).and(qReview.state.isFalse()))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(qReview.id.desc()).fetch();
	}

	@Override
	public List<Integer> getReviewRatesByAccommodationId(Accommodation accommodation) {
		return from(qReview)
			.select(qReview.rate)
			.leftJoin(qReservation)
			.fetchJoin()
			.on(qReservation.id.eq(qReview.reservation.id))
			.leftJoin(qRoom)
			.fetchJoin()
			.on(qRoom.id.eq(qReservation.room.id))
			.leftJoin(qAccommodation)
			.fetchJoin()
			.on(qAccommodation.id.eq(qRoom.accommodation.id))
			.where(qAccommodation.id.eq(accommodation.getId())).fetch();
	}

	@Override
	public Page<ReviewDetailsResponse> getReviewListPageByAccommodationId(Long accommodationId, Long roomId,
		Pageable pageable) {
		List<ReviewDetailsResponse> reviewDetailsResponseList = getReviewDetailsResponseList(accommodationId, roomId,
			pageable);

		Long reviewAmount = countReviewAmountByAccommodationId(accommodationId, roomId);

		return new PageImpl<>(reviewDetailsResponseList, pageable, reviewAmount);
	}

	private List<ReviewDetailsResponse> getReviewDetailsResponseList(Long accommodationId, Long roomId,
		Pageable pageable) {
		return from(qReview)
			.select(Projections.constructor(ReviewDetailsResponse.class,
				qReview.id, qRoom.roomName, qReview.user.id, qReview.rate, qReview.content, qReservation.id))
			.leftJoin(qReservation)
			.fetchJoin()
			.on(qReservation.id.eq(qReview.reservation.id))
			.leftJoin(qRoom)
			.fetchJoin()
			.on(qRoom.id.eq(qReservation.room.id))
			.where(qRoom.accommodation.id.eq(accommodationId), eqRoomId(roomId), qReview.state.isFalse())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(qReview.id.desc()).fetch();
	}

	private Long countReviewAmountByAccommodationId(Long accommodationId, Long roomId) {
		return from(qReview)
			.select(qReview.count())
			.leftJoin(qReservation)
			.fetchJoin()
			.on(qReservation.id.eq(qReview.reservation.id))
			.leftJoin(qRoom)
			.fetchJoin()
			.on(qRoom.id.eq(qReservation.room.id))
			.where(qRoom.accommodation.id.eq(accommodationId), eqRoomId(roomId), qReview.state.isFalse())
			.fetchOne();
	}

	public BooleanExpression eqRoomId(Long roomId) {
		return roomId != null ? qRoom.id.eq(roomId) : null;
	}
}
