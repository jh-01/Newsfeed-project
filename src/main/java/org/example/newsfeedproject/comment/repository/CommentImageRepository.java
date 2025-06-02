package org.example.newsfeedproject.comment.repository;

import org.example.newsfeedproject.comment.entity.CommentImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentImageRepository extends JpaRepository<CommentImage, Long>, QCommentRepository {
}
