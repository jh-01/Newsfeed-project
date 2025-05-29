package org.example.newsfeedproject.feed.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.feed.dto.FeedRequestDto;
import org.example.newsfeedproject.feed.dto.FeedResponseDto;
import org.example.newsfeedproject.feed.service.FeedService;
import org.example.newsfeedproject.user.entity.User;
import org.example.newsfeedproject.user.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시글(Feed)에 대한 HTTP 요청을 처리하는 컨트롤러
 * - 게시글 작성, 조회, 수정, 삭제 API 제공
 */
@RestController
@RequestMapping("/api/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    /**
     * 게시글 생성 API
     */
    @PostMapping
    public ResponseEntity<FeedResponseDto> createFeed(
            @RequestBody FeedRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        FeedResponseDto responseDto = feedService.createFeed(requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 게시글 단건 조회 API
     */
    @GetMapping("/{id}")
    public ResponseEntity<FeedResponseDto> getFeed(@PathVariable Long id) {
        return ResponseEntity.ok(feedService.getFeed(id));
    }

    /**
     * 전체 게시글 목록 조회 API (최신순)
     */
    @GetMapping
    public ResponseEntity<List<FeedResponseDto>> getAllFeeds() {
        return ResponseEntity.ok(feedService.getAllFeeds());
    }

    /**
     * 게시글 수정 API (작성자 본인만 가능)
     */
    @PutMapping("/{id}")
    public ResponseEntity<FeedResponseDto> updateFeed(
            @PathVariable Long id,
            @RequestBody FeedRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        FeedResponseDto updated = feedService.updateFeed(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok(updated);
    }

    /**
     * 게시글 삭제 API (작성자 본인만 가능)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeed(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        feedService.deleteFeed(id, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
