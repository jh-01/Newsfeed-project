package org.example.newsfeedproject.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FriendResponseDto {

    private final String nickname;

    private final String email;

    // memberResponseDto로 옮겨야함
    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(member.getId(), member.getNickname(), member.getEmail());
    }
}
