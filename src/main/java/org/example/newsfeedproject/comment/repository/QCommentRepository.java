package org.example.newsfeedproject.comment.repository;

import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.comment.entity.Comment;

import java.util.List;

public interface QCommentRepository {
    List<CommentResponse> findAllByFeedId(Long feedId, Long userId);
    void deleteByFeedId(Long feedId);
    Comment findByIdOrElseThrow(Long id);
}
