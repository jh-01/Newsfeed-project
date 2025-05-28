package org.example.newsfeedproject.user.dto;

import lombok.Getter;

@Getter
public class UpdateProfileDto {
    private final String email;
    private final String nickname;

    public UpdateProfileDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
