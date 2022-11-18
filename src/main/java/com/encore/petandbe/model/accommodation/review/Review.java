package com.encore.petandbe.model.accommodation.review;

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
public class Review extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
	private User userId;

	@Column
	private Integer rate;

	@Column(length = 3000)
	private String content;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Review(Long id, User userId, Integer rate, String content, Boolean state) {
		this.id = id;
		this.userId = userId;
		this.rate = rate;
		this.content = content;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Review{" +
			"id=" + id +
			", userId=" + userId +
			", rate=" + rate +
			", content='" + content + '\'' +
			", state=" + state +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Review review = (Review)o;
		return Objects.equals(id, review.id) && Objects.equals(userId, review.userId)
			&& Objects.equals(rate, review.rate) && Objects.equals(content, review.content)
			&& Objects.equals(state, review.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userId, rate, content, state);
	}
}
