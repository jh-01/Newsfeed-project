package org.example.newsfeedproject.like.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.feed.entity.Feed;
import org.example.newsfeedproject.feed.repository.FeedRepository;
import org.example.newsfeedproject.like.entity.FeedLike;
import org.example.newsfeedproject.like.repository.FeedLikeRepository;
import org.example.newsfeedproject.user.dto.SessionUserDto;
import org.example.newsfeedproject.user.entity.User;
import org.example.newsfeedproject.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedLikeService {

    private final FeedLikeRepository feedlikeRepository;
    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    public void feedLike(Long id, HttpServletRequest request) {

        SessionUserDto user = (SessionUserDto) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(user.getId());

        Feed feed = feedRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 피드를 찾을 수 없습니다."));

        if (me.getId().equals(feed.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인의 게시물에 좋아요는 불가합니다.");
        }

        FeedLike feedLike = feedlikeRepository.findByUserIdAndFeedId(me, feed);

        if (feedLike != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복 좋아요는 불가합니다.");
        }

        feedLike = new FeedLike(me, feed);

        feedlikeRepository.save(feedLike);

    }

    public void deleteFeedLike(Long id, HttpServletRequest request) {

        SessionUserDto user = (SessionUserDto) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(user.getId());

        Feed feed = feedRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 피드를 찾을 수 없습니다."));

        FeedLike feedLike = feedlikeRepository.findByUserIdAndFeedId(me, feed);

        if (feedLike == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "취소할 좋아요가 없습니다.");
        }

        feedlikeRepository.delete(feedLike);

    }


}
