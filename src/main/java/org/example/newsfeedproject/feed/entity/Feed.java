package org.example.newsfeedproject.feed.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.example.newsfeedproject.user.entity.User;

@Entity
@Table(name = "feed")
@Getter
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Feed(Long id){
        this.id = id;
    }

    public Feed() {

    }
}
