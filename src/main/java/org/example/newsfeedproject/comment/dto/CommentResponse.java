package org.example.newsfeedproject.comment.dto;

import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private Long feedId;
    private String nickname;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long likeCount;
    private boolean isLiked;
}