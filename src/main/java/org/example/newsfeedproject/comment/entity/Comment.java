package org.example.newsfeedproject.comment.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.newsfeedproject.common.entity.BaseTimeEntity;
import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.feed.entity.Feed;
import org.example.newsfeedproject.user.entity.User;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @Column(nullable = false, length = 255)
    @Setter
    private String comments;

    @OneToOne(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private CommentImage commentImage;

    public Comment(User user, Feed feed, String comments, CommentImage commentImage){
        this.user = user;
        this.feed = feed;
        this.comments = comments;
        this.commentImage = commentImage;
    }

    public static CommentResponse toDto(Comment comment){
        return new CommentResponse(
                comment.id,
                comment.feed.getId(),
                comment.user.getNickname(),
                comment.comments,
                comment.getCommentImage().getPath(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                0L,
                false
        );
    }
}
