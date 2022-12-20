package com.encore.petandbe.model.accommodation.reservation;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.encore.petandbe.model.BaseEntity;
import com.encore.petandbe.model.accommodation.room.Room;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.service.accommodation.reservation.dto.ReservationUpdatingdto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "state = false")
public class Reservation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "room_id")
	private Room room;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime checkInDate;

	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime checkOutDate;

	@Column(nullable = false)
	private Integer amount;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public void deleteReservation() {
		this.state = true;
	}

	public Reservation(Long id, Room room, User user, LocalDateTime checkInDate, LocalDateTime checkOutDate,
		Integer amount,
		Boolean state) {
		this.id = id;
		this.room = room;
		this.user = user;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.amount = amount;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Reservation{" +
			"id=" + id +
			", checkInDate=" + checkInDate +
			", checkOutDate=" + checkOutDate +
			", amount=" + amount +
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
		return Objects.equals(id, that.id) && Objects.equals(room, that.room)
			&& Objects.equals(user, that.user) && Objects.equals(checkInDate, that.checkInDate)
			&& Objects.equals(checkOutDate, that.checkOutDate) && Objects.equals(amount, that.amount)
			&& Objects.equals(state, that.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, checkInDate, checkOutDate, amount, state);
	}

	public void updateReservation(ReservationUpdatingdto reservationUpdatingdto) {
		this.checkInDate = reservationUpdatingdto.getCheckInDate();
		this.checkOutDate = reservationUpdatingdto.getCheckOutDate();
		this.amount = reservationUpdatingdto.getAmount();
	}
}