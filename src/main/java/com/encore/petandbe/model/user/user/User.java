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

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
	private String userTokenId;

	@Column(nullable = false, length = 30, unique = true)
	private String username;

	@Column(length = 100)
	private String password;

	@Column(nullable = false, length = 10, unique = true)
	private String nickname;

	@Column(nullable = false, length = 123)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	public void becomeHost(Host host) {
		this.host = host;
	}

	public User(Long id, Host host, String userTokenId, String username, String password, String nickname,
		String email,
		Role role) {
		this.id = id;
		this.host = host;
		this.userTokenId = userTokenId;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", hostId=" + host +
			", userTokenId='" + userTokenId + '\'' +
			", username='" + username + '\'' +
			", password='" + password + '\'' +
			", nickname='" + nickname + '\'' +
			", email='" + email + '\'' +
			", role=" + role +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User)o;
		return Objects.equals(id, user.id) && Objects.equals(host, user.host)
			&& Objects.equals(userTokenId, user.userTokenId) && Objects.equals(username, user.username)
			&& Objects.equals(password, user.password) && Objects.equals(nickname, user.nickname)
			&& Objects.equals(email, user.email) && role == user.role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, host, userTokenId, username, password, nickname, email, role);
	}
}