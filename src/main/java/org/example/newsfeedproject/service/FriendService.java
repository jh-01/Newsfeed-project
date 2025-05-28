package org.example.newsfeedproject.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.dto.friend.FindFriendResponseDto;
import org.example.newsfeedproject.dto.friend.FriendResponseDto;
import org.example.newsfeedproject.dto.friend.SearchFriendsResponseDto;
import org.example.newsfeedproject.dto.friend.SearchFriendsResponseDto;
import org.example.newsfeedproject.entity.Friend;
import org.example.newsfeedproject.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public List<SearchFriendsResponseDto> search(String nickname){

        return memberRepository.findAllByNickname(nickname).stream().map(MemberResponseDto::toDto).toList();
    }

    public FriendResponseDto add(HttpServletRequest request, Long id){

        Member me = memberRepository.findByIdOrElseThrow(request.getSession().getAttribute().getId());

        Member friend = memberRepository.findByIdOrElseThrow(id);

        Friend addFriend = new Friend(me, friend);

        friendRepository.save(addFriend);

        return new FriendResponseDto(friend.getName(), friend.getEmail());
    }

    public void delete(Long id){

        Member me = memberRepository.findByIdOrElseThrow(request.getSession().getAttribute().getId());

        Member friend = memberRepository.findByIdOrElseThrow(id);

        Friend deleteFriend = new Friend(me, friend);

        friendRepository.delete(deleteFriend);
    }

    public FindFriendResponseDto find(Long id){



        Member friend = memberRepositofy.findByIdOrElseThrow(id);

    }
}
