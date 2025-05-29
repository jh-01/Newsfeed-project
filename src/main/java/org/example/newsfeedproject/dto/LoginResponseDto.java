package org.example.newsfeedproject.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final String nickname;
    private final String email;
    private final String message;

    public LoginResponseDto(String nickname, String email, String message) {
        this.nickname = nickname;
        this.email = email;
        this.message = message;
    }
}
