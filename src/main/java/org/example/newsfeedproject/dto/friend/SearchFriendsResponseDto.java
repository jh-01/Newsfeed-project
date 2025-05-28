package org.example.newsfeedproject.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchFriendsResponseDto {

    private final Long id;

    private final String nickname;

    private final String email;

}
