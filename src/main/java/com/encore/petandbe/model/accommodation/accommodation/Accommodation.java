package com.encore.petandbe.model.accommodation.accommodation;

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
import org.hibernate.annotations.Where;

import com.encore.petandbe.model.BaseEntity;
import com.encore.petandbe.model.accommodation.address.Address;
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
@Where(clause = "state = false")
public class Accommodation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "address_code")
	private Address address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@Column(nullable = false, length = 100)
	private String accommodationName;

	@Column(nullable = false, length = 8)
	private String workingHours;

	@Column(nullable = false, length = 8)
	private String weekendWorkingHours;

	@Column(nullable = false, length = 10)
	private String location;

	@Column(nullable = false, length = 8)
	private String lotNumber;

	@Column(nullable = false, length = 128)
	private String addressDetail;

	@Column(nullable = false, length = 16)
	private AccommodationType accommodationType;

	@Column
	private Double averageRate;

	@Column
	private String detailInfo;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public void updateAvgRate(double newAvgRate) {
		this.averageRate = newAvgRate;
	}

	public Accommodation(Long id, Address address, User user, String accommodationName, String workingHours,
		String weekendWorkingHours, String hotelLocation, String lotNumber, String addressDetail, AccommodationType accomoodationType,
		Double avgRate, String detailInfo, Boolean state) {
		this.id = id;
		this.address = address;
		this.user = user;
		this.accommodationName = accommodationName;
		this.workingHours = workingHours;
		this.weekendWorkingHours = weekendWorkingHours;
		this.location = hotelLocation;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.accommodationType = accomoodationType;
		this.averageRate = avgRate;
		this.detailInfo = detailInfo;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Accommodation{" +
			"id=" + id +
			", accommodationName='" + accommodationName + '\'' +
			", workingHours='" + workingHours + '\'' +
			", wkWorkingHours='" + weekendWorkingHours + '\'' +
			", hotelLocation='" + location + '\'' +
			", lotNumber='" + lotNumber + '\'' +
			", addressDetail='" + addressDetail + '\'' +
			", accommodationType='" + accommodationType + '\'' +
			", avgRate=" + averageRate +
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
		Accommodation that = (Accommodation)o;
		return Objects.equals(id, that.id) && Objects.equals(address, that.address)
			&& Objects.equals(user, that.user) && Objects.equals(accommodationName,
			that.accommodationName) && Objects.equals(workingHours, that.workingHours)
			&& Objects.equals(weekendWorkingHours, that.weekendWorkingHours) && Objects.equals(location,
			that.location) && Objects.equals(lotNumber, that.lotNumber) && Objects.equals(
			addressDetail, that.addressDetail) && Objects.equals(accommodationType, that.accommodationType)
			&& Objects.equals(averageRate, that.averageRate) && Objects.equals(detailInfo, that.detailInfo)
			&& Objects.equals(state, that.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, accommodationName, workingHours, weekendWorkingHours, location, lotNumber,
			addressDetail, accommodationType, averageRate, detailInfo, state);
	}
}
