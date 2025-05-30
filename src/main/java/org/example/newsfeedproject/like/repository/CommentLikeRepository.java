package org.example.newsfeedproject.like.repository;

import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.like.entity.CommentLike;
import org.example.newsfeedproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByUserIdAndCommentId(User userId, Comment commentId);

}
