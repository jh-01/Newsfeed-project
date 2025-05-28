package org.example.newsfeedproject.domain.feed.entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.apache.logging.log4j.util.Strings;
import org.example.newsfeedproject.domain.user.entity.User;

@Entity
@Table(name = "feed")
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
}
