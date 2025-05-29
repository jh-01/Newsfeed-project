package org.example.newsfeedproject.friend.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.friend.dto.FindFriendResponseDto;
import org.example.newsfeedproject.friend.dto.FriendResponseDto;
import org.example.newsfeedproject.friend.dto.SearchUsersResponseDto;
import org.example.newsfeedproject.friend.entity.Friend;
import org.example.newsfeedproject.friend.repository.FriendRepository;
import org.example.newsfeedproject.user.dto.UserResponseDto;
import org.example.newsfeedproject.user.entity.User;
import org.example.newsfeedproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

      // 유저로 옮겨야함
//    public List<SearchUsersResponseDto> search(String nickname){
//
//        return UserRepository.findAllByNickname(nickname).stream().map(UserResponseDto::toDto).toList();
//    }

    public FriendResponseDto add(HttpServletRequest request, Long id){

        User loginUser = (User) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(loginUser.getId());

        User friend = userRepository.findByIdOrElseThrow(id);

        Friend addFriend = new Friend(me, friend);

        friendRepository.save(addFriend);

        return new FriendResponseDto(friend.getNickname(), friend.getEmail());
    }

    public void delete(HttpServletRequest request, Long id){

        User loginUser = (User) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(loginUser.getId());

        User friend = userRepository.findByIdOrElseThrow(id);

        Friend deleteFriend = new Friend(me, friend);

        friendRepository.delete(deleteFriend);
    }

    public List<FindFriendResponseDto> find(HttpServletRequest request){

        User loginUser = (User) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(loginUser.getId());

        return friendRepository.findAllByUserId(me.getId()).stream().map(FindFriendResponseDto::toDto).toList();

    }
}
