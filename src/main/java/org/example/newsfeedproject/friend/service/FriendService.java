package org.example.newsfeedproject.friend.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.friend.dto.FriendsResponseDto;
import org.example.newsfeedproject.friend.dto.AddFriendResponseDto;
import org.example.newsfeedproject.friend.entity.Friend;
import org.example.newsfeedproject.friend.repository.FriendRepository;
import org.example.newsfeedproject.user.entity.User;
import org.example.newsfeedproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public List<FriendsResponseDto> search(String nickname, HttpServletRequest request){

        User loginUser = (User) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(loginUser.getId());

        return friendRepository.findAllByUserId(me.getId()).stream().map(FriendsResponseDto::toDto).filter(x -> x.getNickname().contains(nickname)).toList();
    }

    public AddFriendResponseDto add(HttpServletRequest request, Long id){

        User loginUser = (User) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(loginUser.getId());

        User friend = userRepository.findByIdOrElseThrow(id);

        Friend addFriend = new Friend(me, friend);

        friendRepository.save(addFriend);

        return new AddFriendResponseDto(friend.getNickname(), friend.getEmail());
    }

    public void delete(HttpServletRequest request, Long id){

        User loginUser = (User) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(loginUser.getId());

        User friend = userRepository.findByIdOrElseThrow(id);

        Friend deleteFriend = new Friend(me, friend);

        friendRepository.delete(deleteFriend);
    }

    public List<FriendsResponseDto> find(HttpServletRequest request){

        User loginUser = (User) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(loginUser.getId());

        return friendRepository.findAllByUserId(me.getId()).stream().map(FriendsResponseDto::toDto).toList();

    }
}
