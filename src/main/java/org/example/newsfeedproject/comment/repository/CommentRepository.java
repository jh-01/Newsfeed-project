package org.example.newsfeedproject.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.example.newsfeedproject.comment.entity.Comment;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, QCommentRepository {

}
