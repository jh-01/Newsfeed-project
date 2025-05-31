package org.example.newsfeedproject.feed.repository;

import org.example.newsfeedproject.feed.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 게시글(Feed) 관련 DB 접근을 담당하는 Repository 인터페이스
 * 기본적인 CRUD 기능은 JpaRepository에서 제공되며,
 * 사용자 정의 쿼리 메서드를 추가로 선언할 수 있음
 */
@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

    /**
     * 특정 사용자가 작성한 게시글 목록을 생성일 기준으로 내림차순 정렬하여 반환
     *
     * @param userId 작성자 ID
     * @return 해당 사용자가 작성한 게시글 목록
     */
    List<Feed> findAllByUser_IdOrderByCreatedAtDesc(Long userId);

    /**
     * 전체 게시글을 생성일 기준으로 내림차순 정렬하여 반환
     * 페이지네이션 처리는 Service 단에서 Pageable로 처리
     */
    List<Feed> findAllByOrderByCreatedAtDesc();

    /**
     * 전체 게시글을 생성일 기준으로 내림차순 정렬하여 페이징 처리하여 반환
     *
     * @param pageable 페이지 정보
     * @return 페이징된 게시글 목록
     */
    Page<Feed> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
