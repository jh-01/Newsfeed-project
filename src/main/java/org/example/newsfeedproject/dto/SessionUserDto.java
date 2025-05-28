package org.example.newsfeedproject.dto;

public class SessionUserDto {

    private final String nickname;
    private final String email;

    public SessionUserDto(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }
}
