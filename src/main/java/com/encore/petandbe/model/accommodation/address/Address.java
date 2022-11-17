package com.encore.petandbe.model.accommodation.address;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.encore.petandbe.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity {

	@Id
	@Column(length = 5, name = "address_code")
	private String addressCode;

	@Column(nullable = false, length = 16)
	private String stateName;

	@Column(nullable = false, length = 32)
	private String cityName;

	public Address(String addressCode, String stateName, String cityName) {
		this.addressCode = addressCode;
		this.stateName = stateName;
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "Address{" +
			"addressCode='" + addressCode + '\'' +
			", stateName='" + stateName + '\'' +
			", cityName='" + cityName + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Address address = (Address)o;
		return Objects.equals(addressCode, address.addressCode) && Objects.equals(stateName, address.stateName)
			&& Objects.equals(cityName, address.cityName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressCode, stateName, cityName);
	}
}
