package org.example.newsfeedproject.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionUserDto {

    private String nickname;
    private String email;
    private Long id;

    public SessionUserDto(String nickname, String email, Long id) {
        this.nickname = nickname;
        this.email = email;
        this.id = id;
    }
}
