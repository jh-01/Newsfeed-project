package org.example.newsfeedproject.feed.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 생성 및 수정 시 사용되는 요청 DTO 클래스
 * 클라이언트로부터 title과 contents 데이터를 받아옴
 */
@Getter
@NoArgsConstructor
public class FeedRequestDto {

    private String title;     // 게시글 제목
    private String contents;  // 게시글 내용
}


