package org.example.newsfeedproject.comment.repository;


import org.example.newsfeedproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.example.newsfeedproject.comment.entity.Comment;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, QCommentRepository {
    Long user(User user);

    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 댓글은 없는 댓글입니다."));
    }
}
