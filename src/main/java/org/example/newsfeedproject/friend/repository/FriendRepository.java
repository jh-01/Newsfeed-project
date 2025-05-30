package org.example.newsfeedproject.friend.repository;

import org.example.newsfeedproject.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    Collection<Object> findAllByNickname(String nickname);

    List<Friend> findAllByUserId(Long id);
}
