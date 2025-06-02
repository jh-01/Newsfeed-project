package org.example.newsfeedproject.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.newsfeedproject.friend.entity.Friend;

@Getter
@AllArgsConstructor
public class FriendsResponseDto {

    private final Long id;

    private final String nickname;

    private final String email;

    public static FriendsResponseDto toDto(Friend friend) {
        return new FriendsResponseDto(friend.getFriendId().getId(), friend.getFriendId().getNickname(), friend.getFriendId().getEmail());
    }
}
