package org.example.newsfeedproject.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.newsfeedproject.friend.entity.Friend;

@Getter
@AllArgsConstructor
public class FindFriendResponseDto {

    private final Long id;

    private final String nickname;

    private final String email;

    public static FindFriendResponseDto toDto(Friend friend) {
        return new FindFriendResponseDto(friend.getId(), friend.getFriendId().getNickname(), friend.getFriendId().getEmail());
    }
}
