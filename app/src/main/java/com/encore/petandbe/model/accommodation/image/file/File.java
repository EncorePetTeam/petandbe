package com.encore.petandbe.model.accommodation.image.file;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.encore.petandbe.model.accommodation.accommodation.Accommodation;
import com.encore.petandbe.model.accommodation.room.Room;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true, name = "accommodation_id")
	private Accommodation accommodation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true, name = "room_id")
	private Room room;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "image_file_id")
	private ImageFile imageFile;

	public File(Long id, Accommodation accommodation, Room room, ImageFile imageFile) {
		this.id = id;
		this.accommodation = accommodation;
		this.room = room;
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "File{" +
			"id=" + id +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		File file = (File)o;
		return Objects.equals(id, file.id) && Objects.equals(accommodation, file.accommodation)
			&& Objects.equals(room, file.room) && Objects.equals(imageFile, file.imageFile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
