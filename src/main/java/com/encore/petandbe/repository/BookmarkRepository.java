package com.encore.petandbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.petandbe.model.accommodation.bookmark.Bookmark;
import com.encore.petandbe.model.accommodation.bookmark.BookmarkId;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
}
