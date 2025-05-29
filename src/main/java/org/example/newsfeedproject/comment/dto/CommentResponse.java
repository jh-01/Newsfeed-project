package org.example.newsfeedproject.comment.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private Long feedId;
    private String nickname;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long likeCount;
    private boolean isLiked;

    @QueryProjection
    public CommentResponse(Long id, Long feedId, String nickname, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, Long likeCount, boolean isLiked) {
        this.id = id;
        this.feedId = feedId;
        this.nickname = nickname;
        this.comments = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
    }


}