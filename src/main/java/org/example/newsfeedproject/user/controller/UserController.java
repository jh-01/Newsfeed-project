package org.example.newsfeedproject.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.constant.Const;
import org.example.newsfeedproject.user.dto.UpdatePasswordDto;
import org.example.newsfeedproject.user.dto.UpdateProfileDto;
import org.example.newsfeedproject.user.dto.UserRequestDto;
import org.example.newsfeedproject.user.dto.UserResponseDto;
import org.example.newsfeedproject.user.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 유저 생성 ( 회원가입 )
    @PostMapping
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequestDto) {

        UserResponseDto singupUserResponseDto = userService.signup(userRequestDto.getEmail(), userRequestDto.getPassword(), userRequestDto.getNickname());

        return new ResponseEntity<>(singupUserResponseDto, HttpStatus.CREATED);
    }

    // 특정 사용자 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 전체 유저 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){

        List<UserResponseDto> findAllUser = userService.findAll();

        return new ResponseEntity<>(findAllUser,HttpStatus.OK);
    }

    // 프로필 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> modifyProfile(
            @PathVariable Long id,
            @RequestBody UpdateProfileDto updateProfileDto,
            HttpServletRequest request
    ) {

        // 세션 가져오기
        HttpSession session = request.getSession();

        // 세션 없을시 404 반환
        if(session == null || session.getAttribute(Const.USER) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserResponseDto userResponseDto = userService.modifyProfile(id, updateProfileDto.getEmail(), updateProfileDto.getNickname());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 비밀번호 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordDto updatePasswordDto,
            HttpServletRequest request) {

        // 세션 가져오기
        HttpSession session = request.getSession();

        // 세션 없을시 404 반환
        if(session == null || session.getAttribute(Const.USER) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 비밀번호 변경 로직
        userService.updatePassword(id, updatePasswordDto.getOldPassword(), updatePasswordDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 삭제 ( 회원 탈퇴 )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){

        // 세션 or Token 확인 작업 필요


        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 친구 조회
    // 친구 로직 보고 만들어야함
//    @GetMapping("/{id}/friends")
//    public ResponseEntity<UserResponseDto> findByFriends(@PathVariable Long id){
//
//    }
}
