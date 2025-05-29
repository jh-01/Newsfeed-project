package org.example.newsfeedproject.comment.repository;


import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.feed.entity.Feed;
import org.example.newsfeedproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.newsfeedproject.comment.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, QCommentRepository {
}
