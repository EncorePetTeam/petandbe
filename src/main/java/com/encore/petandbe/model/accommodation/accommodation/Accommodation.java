package com.encore.petandbe.model.accommodation.accommodation;

import java.time.LocalTime;
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
import com.encore.petandbe.service.accommodation.accomodation.dto.AccommodationUpdatingDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
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

	@Column(nullable = false, columnDefinition = "TIME")
	private LocalTime workingStart;

	@Column(nullable = false, columnDefinition = "TIME")
	private LocalTime workingEnd;

	@Column(nullable = false, columnDefinition = "TIME")
	private LocalTime weekendWorkingStart;

	@Column(nullable = false, columnDefinition = "TIME")
	private LocalTime weekendWorkingEnd;

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

	@Column(columnDefinition = "int default 0")
	private Integer bookmarkCount;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public void updateAvgRate(double newAvgRate) {
		this.averageRate = newAvgRate;
	}

	protected Accommodation(Long id, Address address, User user, String accommodationName, LocalTime workingStart,
		LocalTime workingEnd, LocalTime weekendWorkingStart, LocalTime weekendWorkingEnd, String location,
		String lotNumber,
		String addressDetail, AccommodationType accommodationType, Double averageRate, String detailInfo,
		Integer bookmarkCount, Boolean state) {
		this.id = id;
		this.address = address;
		this.user = user;
		this.accommodationName = accommodationName;
		this.workingStart = workingStart;
		this.workingEnd = workingEnd;
		this.weekendWorkingStart = weekendWorkingStart;
		this.weekendWorkingEnd = weekendWorkingEnd;
		this.location = location;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.accommodationType = accommodationType;
		this.averageRate = averageRate;
		this.detailInfo = detailInfo;
		this.bookmarkCount = bookmarkCount;
		this.state = state;
	}

	public void updateAccommodation(AccommodationUpdatingDTO dto) {
		setAddress(dto.getAddress());
		setAccommodationName(dto.getAccommodationName());
		setWorkingStart(dto.getWorkingStart());
		setWorkingEnd(dto.getWorkingEnd());
		setWeekendWorkingStart(dto.getWeekendWorkingStart());
		setWorkingEnd(dto.getWeekendWorkingEnd());
		setLocation(dto.getLocation());
		setLotNumber(dto.getLotNumber());
		setAddressDetail(dto.getAddressDetail());
		setAccommodationType(dto.getAccommodationType());
		setDetailInfo(dto.getDetailInfo());
	}

	public void deleteAccommodation() {
		setState(true);
	}

	@Override
	public String toString() {
		return "Accommodation{" +
			"id=" + id +
			", accommodationName='" + accommodationName + '\'' +
			", workingStart=" + workingStart +
			", workingEnd=" + workingEnd +
			", weekendWorkingStart=" + weekendWorkingStart +
			", weekendWorkingEnd=" + weekendWorkingEnd +
			", location='" + location + '\'' +
			", lotNumber='" + lotNumber + '\'' +
			", addressDetail='" + addressDetail + '\'' +
			", accommodationType=" + accommodationType +
			", averageRate=" + averageRate +
			", detailInfo='" + detailInfo + '\'' +
			", bookmarkCount=" + bookmarkCount +
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
			that.accommodationName) && Objects.equals(workingStart, that.workingStart)
			&& Objects.equals(workingEnd, that.workingEnd) && Objects.equals(weekendWorkingStart,
			that.weekendWorkingStart) && Objects.equals(weekendWorkingEnd, that.weekendWorkingEnd)
			&& Objects.equals(location, that.location) && Objects.equals(lotNumber, that.lotNumber)
			&& Objects.equals(addressDetail, that.addressDetail) && accommodationType == that.accommodationType
			&& Objects.equals(averageRate, that.averageRate) && Objects.equals(detailInfo,
			that.detailInfo) && Objects.equals(bookmarkCount, that.bookmarkCount) && Objects.equals(
			state, that.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, accommodationName, workingStart, workingEnd, weekendWorkingStart, weekendWorkingEnd,
			location, lotNumber, addressDetail, accommodationType, averageRate, detailInfo, bookmarkCount, state);
	}
}
