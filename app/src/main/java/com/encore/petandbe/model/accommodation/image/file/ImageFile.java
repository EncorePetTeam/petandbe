package com.encore.petandbe.model.accommodation.image.file;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String url;

	public ImageFile(Long id, String url) {
		this.id = id;
		this.url = url;
	}

	@Override
	public String toString() {
		return "ImageFile{" +
			"id=" + id +
			", url='" + url + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ImageFile imageFile = (ImageFile)o;
		return Objects.equals(id, imageFile.id) && Objects.equals(url, imageFile.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, url);
	}
}
