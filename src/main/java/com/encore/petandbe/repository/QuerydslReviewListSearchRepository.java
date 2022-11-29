package com.encore.petandbe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.petandbe.controller.accommodation.review.responses.ReviewWithAccommodationResponse;
import com.encore.petandbe.model.accommodation.accommodation.QAccommodation;
import com.encore.petandbe.model.accommodation.reservation.QReservation;
import com.encore.petandbe.model.accommodation.review.QReview;
import com.encore.petandbe.model.accommodation.review.Review;
import com.querydsl.core.types.Projections;

@Repository
public class QuerydslReviewListSearchRepository extends QuerydslRepositorySupport
	implements ReviewListSearchRepository {

	private QReview qReview = QReview.review;
	private QAccommodation qAccommodation = QAccommodation.accommodation;
	private QReservation qReservation = QReservation.reservation;

	public QuerydslReviewListSearchRepository() {
		super(Review.class);
	}

	@Override
	public Page<ReviewWithAccommodationResponse> getReviewListPage(Long userId, Pageable pageable) {

		List<ReviewWithAccommodationResponse> reviewWithAccommodationResponseList
			= getReviewWithAccommodationResponseList(userId, pageable);

		Long reviewAmount = countReviewAmountByUser(userId);

		return new PageImpl<>(reviewWithAccommodationResponseList, pageable, reviewAmount);
	}

	private Long countReviewAmountByUser(Long userId) {
		return from(qReview)
			.select(qReview.count())
			.where(qReview.user.id.eq(userId))
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
			.on(qAccommodation.id.eq(qReservation.accommodation.id))
			.where(qReview.user.id.eq(userId))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(qReview.id.desc()).fetch();
	}
}
