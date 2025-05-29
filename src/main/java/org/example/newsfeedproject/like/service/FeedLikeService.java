package org.example.newsfeedproject.like.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.like.entity.FeedLike;
import org.example.newsfeedproject.like.repository.FeedLikeRepository;
import org.example.newsfeedproject.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FeedLikeService {

    private final FeedLikeRepository feedlikeRepository;
    private final FeedRepository feedRepository;

    public void feedLike(Long id, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        Feed feed = feedRepository.findByIdOrElseThrow(id);

        FeedLike feedLike = feedlikeRepository.findByUserIdAndFeedId(user.getId(), feed.getId());

        if (feedLike != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복 좋아요는 불가합니다.");
        }

        likeRepository.save(feedLike);

    }

    public void deleteFeedLike(Long id, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        Feed feed = feedRepository.findByIdOrElseThrow(id);

        FeedLike feedLike = new FeedLike(user, feed);

        likeRepository.delete(feedLike);

    }


}
