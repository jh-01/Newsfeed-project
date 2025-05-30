package org.example.newsfeedproject.like.repository;

import org.example.newsfeedproject.like.entity.FeedLike;
import org.example.newsfeedproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {

    Optional<FeedLike> findByUserIdAndFeedId(User userId, Feed feedId);

}
