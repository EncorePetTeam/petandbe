package com.encore.petandbe.model.user.host;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.encore.petandbe.model.BaseEntity;
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
@SQLDelete(sql = "UPDATE host SET state = true WHERE id = ?")
@Where(clause = "state = false")
public class Host extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false, length = 12, unique = true)
	private String registrationNumber;

	@Column(nullable = false, length = 20)
	private String hostName;

	@Column(nullable = false, length = 8)
	private String openDate;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Host(Long id, User user, String registrationNumber, String hostName, String openDate, Boolean state) {
		this.id = id;
		this.user = user;
		this.registrationNumber = registrationNumber;
		this.hostName = hostName;
		this.openDate = openDate;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Host{" +
			"id=" + id +
			", registrationNumber='" + registrationNumber + '\'' +
			", hostName='" + hostName + '\'' +
			", openDate='" + openDate + '\'' +
			", state='" + state + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Host host = (Host)o;
		return Objects.equals(id, host.id) && Objects.equals(registrationNumber,
			host.registrationNumber) && Objects.equals(hostName, host.hostName) && Objects.equals(
			openDate, host.openDate) && Objects.equals(state, host.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, registrationNumber, hostName, openDate, state);
	}
}
