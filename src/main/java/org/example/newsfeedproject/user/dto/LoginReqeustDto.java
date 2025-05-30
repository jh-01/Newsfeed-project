package org.example.newsfeedproject.user.dto;

import lombok.Getter;

@Getter
public class LoginReqeustDto {

    private final String email;
    private final String password;

    public LoginReqeustDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
