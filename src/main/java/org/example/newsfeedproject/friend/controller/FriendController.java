package org.example.newsfeedproject.friend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.friend.dto.FindFriendResponseDto;
import org.example.newsfeedproject.friend.dto.AddFriendRequestDto;
import org.example.newsfeedproject.friend.dto.AddFriendResponseDto;
import org.example.newsfeedproject.friend.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 유저로 옮겨야함
//    @GetMapping("/search")
//    public ResponseEntity<List<SearchUsersResponseDto>> search(@RequestParam String nickname){
//
//        List<SearchUsersResponseDto> searchUsersResponseDtoList = userService.search(nickname);
//
//        return new ResponseEntity<>(searchUsersResponseDtoList, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<AddFriendResponseDto> add(@RequestBody AddFriendRequestDto dto, HttpServletRequest request){

        AddFriendResponseDto friendResponseDto = friendService.add(request, dto.getId());

        return new ResponseEntity<>(friendResponseDto,HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request){

        friendService.delete(request, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FindFriendResponseDto>> find(HttpServletRequest request){

        List<FindFriendResponseDto> findFriendResponseDtoList = friendService.find(request);

        return new ResponseEntity<>(findFriendResponseDtoList, HttpStatus.OK);
    }
}
