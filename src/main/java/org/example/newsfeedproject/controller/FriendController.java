package org.example.newsfeedproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.dto.friend.*;
import org.example.newsfeedproject.dto.friend.SearchFriendsResponseDto;
import org.example.newsfeedproject.entity.Friend;
import org.example.newsfeedproject.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @GetMapping
    public ResponseEntity<List<SearchFriendsResponseDto>> search(@RequestParam String nickname){

        List<SearchFriendsResponseDto> searchFriendsResponseDtoList = friendService.search(nickname);

        return new ResponseEntity<>(searchFriendsResponseDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FriendResponseDto> add(@RequestBody FriendRequestDto dto, HttpServletRequest request){

        FriendResponseDto friendResponseDto = friendService.add(request, dto.getId());

        return new ResponseEntity<>(friendResponseDto,HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        friendService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindFriendResponseDto> find(@PathVariable Long id){

        FindFriendResponseDto findFriendResponseDto = friendService.find(id);

        return new ResponseEntity<>(findFriendResponseDto, HttpStatus.OK);
    }
}
