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
import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;
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
	@JoinColumn(nullable = false, name = "accommodation_id")
	private Accommodation accommodation;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true, name = "review_id")
	private Review review;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@Column(nullable = false, length = 20)
	private String checkInDate;

	@Column(nullable = false, length = 20)
	private String checkOutDate;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PetCategory petCategory;

	@Column(nullable = false, columnDefinition = "decimal")
	private String weight;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Reservation(Long id, Accommodation accommodation, Review review, User user, String checkInDate,
		String checkOutDate, PetCategory petCategory, String weight, Boolean state) {
		this.id = id;
		this.accommodation = accommodation;
		this.review = review;
		this.user = user;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.petCategory = petCategory;
		this.weight = weight;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Reservation{" +
			"id=" + id +
			", checkInDate='" + checkInDate + '\'' +
			", checkOutDate='" + checkOutDate + '\'' +
			", petCategory=" + petCategory +
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
		return Objects.equals(id, that.id) && Objects.equals(accommodation, that.accommodation)
			&& Objects.equals(review, that.review) && Objects.equals(user, that.user)
			&& Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate,
			that.checkOutDate) && petCategory == that.petCategory && Objects.equals(weight, that.weight)
			&& Objects.equals(state, that.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, checkInDate, checkOutDate, petCategory, weight, state);
	}
}
