package org.example.newsfeedproject.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comment_image")
public class CommentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "path")
    private String path;

    @OneToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    public CommentImage(Comment comment, String path){
        this.comment = comment;
        this.path = path;
    }

    public CommentImage(String path){
        this.path = path;
    }
}
