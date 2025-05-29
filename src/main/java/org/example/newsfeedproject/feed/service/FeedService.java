package org.example.newsfeedproject.feed.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.feed.dto.FeedRequestDto;
import org.example.newsfeedproject.feed.dto.FeedResponseDto;
import org.example.newsfeedproject.feed.entity.Feed;
import org.example.newsfeedproject.feed.repository.FeedRepository;
import org.example.newsfeedproject.user.entity.User;
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

    /**
     * 게시글 새로 생성
     *
     * @param requestDto 사용자가 입력한 제목/내용
     * @param user 현재 로그인한 사용자
     * @return 생성된 게시글 정보
     */
    public FeedResponseDto createFeed(FeedRequestDto requestDto, User user) {
        Feed feed = new Feed(user, requestDto.getTitle(), requestDto.getContents());
        Feed savedFeed = feedRepository.save(feed);
        return new FeedResponseDto(savedFeed);
    }

    /**
     * 게시글 단건 조회
     *
     * @param id 게시글 ID
     * @return 조회된 게시글 정보
     */
    public FeedResponseDto getFeed(Long id) {
        Feed feed = findFeed(id);
        return new FeedResponseDto(feed);
    }

    /**
     * 전체 게시글을 최신순으로 조회
     *
     * @return 게시글 리스트
     */
    public List<FeedResponseDto> getAllFeeds() {
        return feedRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(FeedResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 게시글 수정
     *
     * @param id 수정할 게시글 ID
     * @param requestDto 수정할 제목/내용
     * @param user 현재 로그인한 사용자
     * @return 수정된 게시글 정보
     */
    @Transactional
    public FeedResponseDto updateFeed(Long id, FeedRequestDto requestDto, User user) {
        Feed feed = findFeed(id);
        validateWriter(feed, user); // 작성자 확인
        feed.update(requestDto.getTitle(), requestDto.getContents());
        return new FeedResponseDto(feed);
    }

    /**
     * 게시글 삭제
     *
     * @param id 삭제할 게시글 ID
     * @param user 현재 로그인한 사용자
     */
    public void deleteFeed(Long id, User user) {
        Feed feed = findFeed(id);
        validateWriter(feed, user); // 작성자 확인
        feedRepository.delete(feed);
    }

    /**
     * 게시글 ID로 게시글을 조회
     *
     * @param id 게시글 ID
     * @return Feed 객체
     */
    private Feed findFeed(Long id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    /**
     * 게시글의 작성자가 현재 사용자와 같은지 검증
     *
     * @param feed 게시글 객체
     * @param user 현재 로그인한 사용자
     */
    private void validateWriter(Feed feed, User user) {
        if (!feed.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("작성자만 수정/삭제할 수 있습니다.");
        }
    }
}
