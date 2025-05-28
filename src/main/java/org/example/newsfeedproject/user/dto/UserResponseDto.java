package org.example.newsfeedproject.user.dto;


import lombok.Getter;

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
}
