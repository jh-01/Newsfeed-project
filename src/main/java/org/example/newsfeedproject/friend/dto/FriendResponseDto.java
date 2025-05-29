package org.example.newsfeedproject.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.newsfeedproject.user.dto.UserResponseDto;
import org.example.newsfeedproject.user.entity.User;

@Getter
@AllArgsConstructor
public class FriendResponseDto {

    private final String nickname;

    private final String email;

    // UserResponseDto로 옮겨야함
//    public static UserResponseDto toDto(User user) {
//        return new UserResponseDto(user.getId(), user.getNickname(), user.getEmail());
//    }
}
