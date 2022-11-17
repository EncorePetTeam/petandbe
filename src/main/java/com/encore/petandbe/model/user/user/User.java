package com.encore.petandbe.model.user.user;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class User extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	public User(Long id, String username, String password, String nickname, String email, Role role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.role = role;
	}

	public void modify(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User User = (User)o;
		return Objects.equals(id, User.id) && Objects.equals(username, User.username)
			&& Objects.equals(password, User.password) && Objects.equals(nickname, User.nickname)
			&& Objects.equals(email, User.email) && role == User.role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, nickname, email, role);
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", username='" + username + '\'' +
			", password='" + password + '\'' +
			", nickname='" + nickname + '\'' +
			", email='" + email + '\'' +
			", role=" + role +
			'}';
	}

	public String getRoleValue() {
		return this.role.getValue();
	}
}
