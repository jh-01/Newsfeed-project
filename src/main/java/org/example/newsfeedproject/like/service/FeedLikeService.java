package org.example.newsfeedproject.like.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.feed.entity.Feed;
import org.example.newsfeedproject.feed.repository.FeedRepository;
import org.example.newsfeedproject.like.entity.FeedLike;
import org.example.newsfeedproject.like.repository.FeedLikeRepository;
import org.example.newsfeedproject.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedLikeService {

    private final FeedLikeRepository feedlikeRepository;
    private final FeedRepository feedRepository;

    public void feedLike(Long id, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 피드를 찾을 수 없습니다."));


        Optional<FeedLike> optionalFeedLike = feedlikeRepository.findByUserIdAndFeedId(user, feed);

        if (optionalFeedLike.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복 좋아요는 불가합니다.");
        }

        feedlikeRepository.save(optionalFeedLike.get());

    }

    public void deleteFeedLike(Long id, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 피드를 찾을 수 없습니다."));

        FeedLike feedLike = new FeedLike(user, feed);

        feedlikeRepository.delete(feedLike);

    }


}
