package org.example.newsfeedproject.comment.repository;

import org.example.newsfeedproject.comment.dto.CommentResponse;
import java.util.List;

public interface QCommentRepository {
    List<CommentResponse> findAllByFeedId(Long feedId, Long userId);
}
