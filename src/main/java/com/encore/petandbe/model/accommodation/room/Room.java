package com.encore.petandbe.model.accommodation.room;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.encore.petandbe.model.BaseEntity;
import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.filtering.category.PetCategory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE room SET state = true WHERE id = ?")
@Where(clause = "state = false")
public class Room extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "accommodation_id")
	private Accommodation accommodation;

	@Column(nullable = false, length = 30)
	private String roomName;

	@Column(nullable = false)
	private Integer amount;

	@Column(nullable = false, length = 3)
	@Enumerated(EnumType.STRING)
	private PetCategory petCategory;

	@Column(nullable = true, columnDefinition = "decimal")
	private String weight;

	@Column
	private String detailInfo;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Room(Long id, Accommodation accommodation, String roomName, Integer amount, PetCategory petCategory,
		String weight, String detailInfo, Boolean state) {
		this.id = id;
		this.accommodation = accommodation;
		this.roomName = roomName;
		this.amount = amount;
		this.petCategory = petCategory;
		this.weight = weight;
		this.detailInfo = detailInfo;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Room{" +
			"id=" + id +
			", roomName='" + roomName + '\'' +
			", amount=" + amount +
			", petCategory=" + petCategory +
			", weight='" + weight + '\'' +
			", detailInfo='" + detailInfo + '\'' +
			", state=" + state +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Room room = (Room)o;
		return Objects.equals(id, room.id) && Objects.equals(accommodation, room.accommodation)
			&& Objects.equals(roomName, room.roomName) && Objects.equals(amount, room.amount)
			&& petCategory == room.petCategory && Objects.equals(weight, room.weight) && Objects.equals(
			detailInfo, room.detailInfo) && Objects.equals(state, room.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roomName, amount, petCategory, weight, detailInfo, state);
	}
}
