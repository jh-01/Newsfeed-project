package org.example.newsfeedproject.comment.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import org.example.newsfeedproject.comment.entity.Comment;
import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long id;
    private Long feedId;
    private String nickname;
    private String comments;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long likeCount;
    private boolean isLiked;

    @QueryProjection
    public CommentResponse(Long id, Long feedId, String nickname, String content, String image, LocalDateTime createdAt, LocalDateTime modifiedAt, Long likeCount, boolean isLiked) {
        this.id = id;
        this.feedId = feedId;
        this.nickname = nickname;
        this.comments = content;
        this.image = image;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
    }

    public static CommentResponse of(Comment saved, boolean b, long l) {
        return new CommentResponse(
                saved.getId(),
                saved.getFeed().getId(),
                saved.getUser().getNickname(),
                saved.getComments(),
                saved.getCommentImage().getPath(),
                saved.getCreatedAt(),
                saved.getModifiedAt(),
                l,
                b
        );
    }
}