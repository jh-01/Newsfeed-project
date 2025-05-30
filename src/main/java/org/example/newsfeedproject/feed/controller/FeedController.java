package org.example.newsfeedproject.feed.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.feed.dto.FeedRequestDto;
import org.example.newsfeedproject.feed.dto.FeedResponseDto;
import org.example.newsfeedproject.feed.service.FeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시글(Feed)에 대한 HTTP 요청을 처리하는 컨트롤러
 * - 게시글 작성, 조회, 수정, 삭제 API 제공
 * - 스프링 시큐리티 제외한 구조로 테스트 가능하게 임시 변경
 */
@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping
    public ResponseEntity<FeedResponseDto> createFeed(@RequestBody FeedRequestDto requestDto) {
        return ResponseEntity.ok(feedService.createFeed(requestDto, null)); // 인증 미적용 → user = null
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedResponseDto> getFeed(@PathVariable Long id) {
        return ResponseEntity.ok(feedService.getFeed(id));
    }

    @GetMapping
    public ResponseEntity<List<FeedResponseDto>> getAllFeeds() {
        return ResponseEntity.ok(feedService.getAllFeeds());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedResponseDto> updateFeed(
            @PathVariable Long id,
            @RequestBody FeedRequestDto requestDto
    ) {
        return ResponseEntity.ok(feedService.updateFeed(id, requestDto, null)); // 인증 미적용
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Long id) {
        feedService.deleteFeed(id, null); // 인증 미적용
        return ResponseEntity.noContent().build();
    }
}
