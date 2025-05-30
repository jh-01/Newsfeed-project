package org.example.newsfeedproject.friend.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.friend.dto.FindFriendResponseDto;
import org.example.newsfeedproject.friend.dto.AddFriendResponseDto;
import org.example.newsfeedproject.friend.entity.Friend;
import org.example.newsfeedproject.friend.repository.FriendRepository;
import org.example.newsfeedproject.user.entity.User;
import org.example.newsfeedproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<FindFriendResponseDto> find(HttpServletRequest request){

//        User loginUser = (User) request.getSession().getAttribute("user");
//        User me = userRepository.findByIdOrElseThrow(loginUser.getId());
//
//        return friendRepository.findAllByUserId(me.getId()).stream().map(FindFriendResponseDto::toDto).toList();
//

        // 로그인한 유저 세션에서 가져오기
        User loginUser = (User) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(loginUser.getId());

        // 내가 등록한 친구 목록 조회
        List<Friend> friendList = friendRepository.findAllByUserId(me);

        // friendUser 정보만 DTO로 변환해서 반환
        return friendList.stream()
                .map(friend -> FindFriendResponseDto.toDto(friend))
                .collect(Collectors.toList());
    }
}
