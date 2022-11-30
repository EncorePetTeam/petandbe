package com.encore.petandbe.model.accommodation.bookmark;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.user.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkId implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "accommodation_id")
	private Accommodation accommodation;

	public BookmarkId(User user, Accommodation accommodation) {
		this.user = user;
		this.accommodation = accommodation;
	}
}
