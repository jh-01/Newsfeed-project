package org.example.newsfeedproject.dto;

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
