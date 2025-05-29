package org.example.newsfeedproject.user.dto;


import lombok.Getter;
import org.example.newsfeedproject.user.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto{
    private final Long id;
    private final String email;
    private final String nickname;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public UserResponseDto(Long id, String email, String nickname, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserResponseDto toDto(User user){
        return new UserResponseDto(user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }
}
