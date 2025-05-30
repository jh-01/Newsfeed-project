package org.example.newsfeedproject.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddFriendResponseDto {

    private final String nickname;

    private final String email;

}
