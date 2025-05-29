package org.example.newsfeedproject.feed.dto;

        import lombok.Getter;
        import org.example.newsfeedproject.feed.entity.Feed;

/**
 * 게시글 단건 조회 또는 목록 조회 시 사용되는 응답 DTO 클래스
 * Entity로부터 필요한 데이터만 추출하여 반환
 */
@Getter
public class FeedResponseDto {

    private Long id;            // 게시글 ID
    private String title;       // 제목
    private String contents;    // 내용
    private String writer;      // 작성자 닉네임
    private String createdAt;   // 작성 시각
    private String modifiedAt;  // 수정 시각

    public FeedResponseDto(Feed feed) {
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.contents = feed.getContents();
        this.writer = feed.getUser().getNickname(); // User Entity의 getNickname() 기준
        this.createdAt = feed.getCreatedAt().toString();
        this.modifiedAt = feed.getModifiedAt().toString();
    }
}
