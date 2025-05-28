package org.example.newsfeedproject.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.entity.Friend;

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
