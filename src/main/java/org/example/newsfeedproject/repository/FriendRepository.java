package org.example.newsfeedproject.repository;

import org.example.newsfeedproject.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    Collection<Object> findAllByNickname(String nickname);
}
