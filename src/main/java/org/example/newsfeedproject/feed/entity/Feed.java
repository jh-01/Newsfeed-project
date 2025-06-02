package org.example.newsfeedproject.feed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.common.entity.BaseTimeEntity;
import org.example.newsfeedproject.user.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시글(Feed) 정보를 저장하는 엔티티 클래스
 * - 게시글 제목, 내용, 작성자, 생성/수정 시간 포함
 * - User와 다대일(N:1) 관계로 매핑
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "feed")
public class Feed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 게시글 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 게시글 작성자 (User와 연관관계)

    @Column(nullable = false, length = 20)
    private String title; // 게시글 제목 (최대 20자)

    @Column(nullable = false, length = 255)
    private String contents; // 게시글 내용 (최대 255자)

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /**
     * 게시글 생성 시 사용하는 생성자
     * 작성자, 제목, 내용을 설정하고 생성/수정 시간을 현재 시간으로 초기화
     */
    public Feed(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    /**
     * 게시글 수정 시 사용하는 메서드
     * 제목과 내용을 변경하고 수정 시간을 갱신
     *
     * @param title    변경할 제목
     * @param contents 변경할 내용
     */
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
