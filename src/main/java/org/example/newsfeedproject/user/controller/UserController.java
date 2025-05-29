package org.example.newsfeedproject.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newsfeedproject.user.dto.UpdatePasswordDto;
import org.example.newsfeedproject.user.dto.UpdateProfileDto;
import org.example.newsfeedproject.user.dto.UserRequestDto;
import org.example.newsfeedproject.user.dto.UserResponseDto;
import org.example.newsfeedproject.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<UserResponseDto> findById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        // 생성된 Session 가져오기
        HttpSession session = request.getSession();

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 전체 유저 조회
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> findAll(HttpServletRequest request){

        // 생성된 Session 가져오기
        HttpSession session = request.getSession();

        List<UserResponseDto> findAllUser = userService.findAll();

        return new ResponseEntity<>(findAllUser,HttpStatus.OK);
    }

    // 프로필 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> modifyProfile(
            @PathVariable Long id,
            @RequestBody UpdateProfileDto updateProfileDto,
            HttpServletRequest request) {

        // 생성된 Session 가져오기
        HttpSession session = request.getSession();

        UserResponseDto userResponseDto = userService.modifyProfile(id, updateProfileDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 비밀번호 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordDto updatePasswordDto,
            HttpServletRequest request
    ) {

        // 생성된 Session 가져오기
        HttpSession session = request.getSession();

        userService.updatePassword(id, updatePasswordDto.getOldPassword(), updatePasswordDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 삭제 ( 회원 탈퇴 )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id,
            HttpServletRequest request
            ){

        // 생성된 Session 가져오기
        HttpSession session = request.getSession();

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
