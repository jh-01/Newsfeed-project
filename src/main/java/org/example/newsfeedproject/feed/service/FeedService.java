package org.example.newsfeedproject.feed.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.feed.dto.FeedRequestDto;
import org.example.newsfeedproject.feed.dto.FeedResponseDto;
import org.example.newsfeedproject.feed.entity.Feed;
import org.example.newsfeedproject.feed.repository.FeedRepository;
import org.example.newsfeedproject.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글(Feed)의 비즈니스 로직을 처리하는 서비스 클래스
 * - 게시글 작성, 조회, 수정, 삭제 기능 포함
 * - 작성자 권한 확인 로직 포함
 */
@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    public FeedResponseDto createFeed(FeedRequestDto requestDto, User user) {
        validateUser(user); // 인증되지 않은 유저 차단
        Feed feed = new Feed(user, requestDto.getTitle(), requestDto.getContents());
        Feed savedFeed = feedRepository.save(feed);
        return new FeedResponseDto(savedFeed);
    }

    public FeedResponseDto getFeed(Long id) {
        Feed feed = findFeed(id);
        return new FeedResponseDto(feed);
    }

    public List<FeedResponseDto> getAllFeeds() {
        return feedRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(FeedResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 전체 게시글을 페이징 처리하여 최신순으로 조회
     *
     * @param pageable 페이지 정보
     * @return 페이징된 게시글 리스트
     */
    public List<FeedResponseDto> getFeedsByPage(Pageable pageable) {
        return feedRepository.findAllByOrderByCreatedAtDesc(pageable).stream()
                .map(FeedResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public FeedResponseDto updateFeed(Long id, FeedRequestDto requestDto, User user) {
        validateUser(user); // 인증되지 않은 유저 차단
        Feed feed = findFeed(id);
        validateWriter(feed, user); // 작성자 확인
        feed.update(requestDto.getTitle(), requestDto.getContents());
        return new FeedResponseDto(feed);
    }

    public void deleteFeed(Long id, User user) {
        validateUser(user); // 인증되지 않은 유저 차단
        Feed feed = findFeed(id);
        validateWriter(feed, user); // 작성자 확인
        feedRepository.delete(feed);
    }

    private Feed findFeed(Long id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    private void validateWriter(Feed feed, User user) {
        if (!feed.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("작성자만 수정/삭제할 수 있습니다.");
        }
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("로그인 후 이용해 주세요.");
        }
    }
}
