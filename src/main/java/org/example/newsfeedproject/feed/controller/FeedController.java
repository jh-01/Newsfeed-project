package org.example.newsfeedproject.feed.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.feed.dto.FeedRequestDto;
import org.example.newsfeedproject.feed.dto.FeedResponseDto;
import org.example.newsfeedproject.feed.service.FeedService;
import org.example.newsfeedproject.user.dto.SessionUserDto;
import org.example.newsfeedproject.user.entity.User;
import org.example.newsfeedproject.user.repository.UserRepository;
import org.example.newsfeedproject.user.constant.Const;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<FeedResponseDto> createFeed(
            @RequestBody FeedRequestDto requestDto,
            HttpServletRequest request
    ) {
        // 세션에서 로그인된 유저 정보 추출
        User user = getSessionUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedService.createFeed(requestDto, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedResponseDto> getFeed(@PathVariable Long id) {
        return ResponseEntity.ok(feedService.getFeed(id));
    }

    /**
     * 전체 게시글 목록 조회 API (페이징 처리 + 최신순)
     *
     * @param page 페이지 번호 (0부터 시작)
     * @return FeedResponseDto 리스트
     */
    @GetMapping
    public ResponseEntity<List<FeedResponseDto>> getAllFeeds(
            @RequestParam(defaultValue = "0") int page
    ) {
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("createdAt").descending());
        return ResponseEntity.ok(feedService.getFeedsByPage(pageRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedResponseDto> updateFeed(
            @PathVariable Long id,
            @RequestBody FeedRequestDto requestDto,
            HttpServletRequest request
    ) {
        // 세션에서 로그인된 유저 정보 추출
        User user = getSessionUser(request);
        return ResponseEntity.ok(feedService.updateFeed(id, requestDto, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeed(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        // 세션에서 로그인된 유저 정보 추출
        User user = getSessionUser(request);
        feedService.deleteFeed(id, user);
        return ResponseEntity.noContent().build();
    }

    /**
     * 세션에서 로그인한 유저 정보를 가져와 User 엔티티로 반환
     * @param request Http 요청 객체
     * @return 로그인한 User 엔티티
     */
    private User getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(Const.USER) == null) {
            throw new IllegalArgumentException("로그인 후 이용해 주세요.");
        }

        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(Const.USER);
        return userRepository.findByIdOrElseThrow(sessionUserDto.getId());
    }
}
