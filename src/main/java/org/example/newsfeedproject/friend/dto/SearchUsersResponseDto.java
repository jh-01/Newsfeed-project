package org.example.newsfeedproject.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchUsersResponseDto {

    private final Long id;

    private final String nickname;

    private final String email;

}
