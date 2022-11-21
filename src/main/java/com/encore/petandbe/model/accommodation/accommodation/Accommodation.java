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
import org.hibernate.annotations.SQLDelete;
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
@SQLDelete(sql = "UPDATE accommodation SET state = true WHERE id = ?")
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
	private String wkWorkingHours;

	@Column(nullable = false, length = 10)
	private String hotelLocation;

	@Column(nullable = false, length = 8)
	private String lotNumber;

	@Column(nullable = false, length = 128)
	private String addressDetail;

	@Column(nullable = false, length = 16)
	private String accommodationType;

	@Column
	private Double avgRate;

	@Column
	private String detailInfo;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public void updateAvgRate(double newAvgRate) {
		this.avgRate = newAvgRate;
	}

	public Accommodation(Long id, Address address, User user, String accommodationName, String workingHours,
		String wkWorkingHours, String hotelLocation, String lotNumber, String addressDetail, String accommodationType,
		Double avgRate, String detailInfo, Boolean state) {
		this.id = id;
		this.address = address;
		this.user = user;
		this.accommodationName = accommodationName;
		this.workingHours = workingHours;
		this.wkWorkingHours = wkWorkingHours;
		this.hotelLocation = hotelLocation;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.accommodationType = accommodationType;
		this.avgRate = avgRate;
		this.detailInfo = detailInfo;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Accommodation{" +
			"id=" + id +
			", accommodationName='" + accommodationName + '\'' +
			", workingHours='" + workingHours + '\'' +
			", wkWorkingHours='" + wkWorkingHours + '\'' +
			", hotelLocation='" + hotelLocation + '\'' +
			", lotNumber='" + lotNumber + '\'' +
			", addressDetail='" + addressDetail + '\'' +
			", accomoodationType='" + accommodationType + '\'' +
			", avgRate=" + avgRate +
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
			&& Objects.equals(wkWorkingHours, that.wkWorkingHours) && Objects.equals(hotelLocation,
			that.hotelLocation) && Objects.equals(lotNumber, that.lotNumber) && Objects.equals(
			addressDetail, that.addressDetail) && Objects.equals(accommodationType, that.accommodationType)
			&& Objects.equals(avgRate, that.avgRate) && Objects.equals(detailInfo, that.detailInfo)
			&& Objects.equals(state, that.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, accommodationName, workingHours, wkWorkingHours, hotelLocation, lotNumber,
			addressDetail,
			accommodationType, avgRate, detailInfo, state);
	}
}
