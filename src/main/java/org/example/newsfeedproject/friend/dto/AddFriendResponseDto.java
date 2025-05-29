package org.example.newsfeedproject.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddFriendResponseDto {

    private final String nickname;

    private final String email;

    // UserResponseDto로 옮겨야함
//    public static UserResponseDto toDto(User user) {
//        return new UserResponseDto(user.getId(), user.getNickname(), user.getEmail());
//    }
}
