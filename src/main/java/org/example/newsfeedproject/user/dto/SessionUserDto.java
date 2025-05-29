package org.example.newsfeedproject.user.dto;

public class SessionUserDto {

    private final String nickname;
    private final String email;
    private final Long id;

    public SessionUserDto(String nickname, String email, Long id) {
        this.nickname = nickname;
        this.email = email;
        this.id = id;
    }
}
