package com.encore.petandbe.model.accommodation.reservation;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;

import com.encore.petandbe.model.BaseEntity;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.filtering.category.PetCategoryEunm;
import com.encore.petandbe.model.accommodation.review.Review;
import com.encore.petandbe.model.user.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "accommodation_id", referencedColumnName = "id")
	private Accommodation accommodationId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true, name = "review_id", referencedColumnName = "id")
	private Review reviewId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
	private User userId;

	@Column(nullable = false, length = 20)
	private String checkInDate;

	@Column(nullable = false, length = 20)
	private String checkOutDate;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PetCategoryEunm petCategoryEnum;

	@Column(nullable = false, columnDefinition = "decimal")
	private String weight;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Reservation(Long id, Accommodation accommodationId, Review reviewId, User userId, String checkInDate,
		String checkOutDate, PetCategoryEunm petCategoryEnum, String weight, Boolean state) {
		this.id = id;
		this.accommodationId = accommodationId;
		this.reviewId = reviewId;
		this.userId = userId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.petCategoryEnum = petCategoryEnum;
		this.weight = weight;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Reservation{" +
			"id=" + id +
			", accommodationId=" + accommodationId +
			", reviewId=" + reviewId +
			", userId=" + userId +
			", checkInDate='" + checkInDate + '\'' +
			", checkOutDate='" + checkOutDate + '\'' +
			", petCategoryEnum=" + petCategoryEnum +
			", weight='" + weight + '\'' +
			", state=" + state +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Reservation that = (Reservation)o;
		return Objects.equals(id, that.id) && Objects.equals(accommodationId, that.accommodationId)
			&& Objects.equals(reviewId, that.reviewId) && Objects.equals(userId, that.userId)
			&& Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate,
			that.checkOutDate) && petCategoryEnum == that.petCategoryEnum && Objects.equals(weight, that.weight)
			&& Objects.equals(state, that.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, accommodationId, reviewId, userId, checkInDate, checkOutDate, petCategoryEnum, weight,
			state);
	}
}
