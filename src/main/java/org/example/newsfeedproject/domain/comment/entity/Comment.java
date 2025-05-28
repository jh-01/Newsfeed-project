package org.example.newsfeedproject.domain.comment.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.example.newsfeedproject.domain.comment.CommentRepository;
import org.example.newsfeedproject.domain.feed.entity.Feed;
import org.example.newsfeedproject.domain.user.entity.User;

@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @Column(nullable = false)
    private String comments;

    private Comment(User user, Feed feed,Comment comment){

    }
}
