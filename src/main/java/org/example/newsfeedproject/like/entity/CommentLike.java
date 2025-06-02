package org.example.newsfeedproject.like.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.user.entity.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment_likes")
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment commentId;


    public CommentLike(User user, Comment comment) {
        this.commentId = comment;
        this.userId = user;
    }
}
