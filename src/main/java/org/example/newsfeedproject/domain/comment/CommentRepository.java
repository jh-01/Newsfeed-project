package org.example.newsfeedproject.domain.comment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.newsfeedproject.domain.comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, QCommentRepository {

}
