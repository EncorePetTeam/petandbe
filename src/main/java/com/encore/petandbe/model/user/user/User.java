package com.encore.petandbe.model.user.user;

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
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;

import com.encore.petandbe.model.BaseEntity;
import com.encore.petandbe.model.user.host.Host;
import com.encore.petandbe.service.user.user.dto.UserUpdateDTO;

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
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true, name = "host_id")
	private Host host;

	@Column(nullable = false, length = 100, unique = true)
	private String userCode;

	@Column(nullable = false, length = 10, unique = true)
	private String nickname;

	@Column(nullable = true, length = 123)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public void becomeHost(Host host) {
		this.host = host;
	}

	public User(Long id, Host host, String userCode, String nickname, String email, Role role, Boolean state) {
		this.id = id;
		this.host = host;
		this.userCode = userCode;
		this.nickname = nickname;
		this.email = email;
		this.role = role;
		this.state = state;
	}

	public void updateUser(UserUpdateDTO dto){
		setNickname(dto.getNickname());
		setEmail(dto.getEmail());
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", host=" + host +
			", userCode='" + userCode + '\'' +
			", nickname='" + nickname + '\'' +
			", email='" + email + '\'' +
			", role=" + role +
			", state=" + state +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User)o;
		return Objects.equals(id, user.id) && Objects.equals(host, user.host)
			&& Objects.equals(userCode, user.userCode) && Objects.equals(nickname, user.nickname)
			&& Objects.equals(email, user.email) && role == user.role && Objects.equals(state,
			user.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, host, userCode, nickname, email, role, state);
	}
}