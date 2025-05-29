package org.example.newsfeedproject.user.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private final String email;
    private final String password;
    private final String nickname;

    public UserRequestDto( String email, String password,String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
