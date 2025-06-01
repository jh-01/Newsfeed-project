package org.example.newsfeedproject.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.user.constant.Const;
import org.example.newsfeedproject.user.dto.*;
import org.example.newsfeedproject.user.service.UserService;
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
    @GetMapping("/all")
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

        // 검증을 위한 다운 캐스팅
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(Const.USER);

        // 유저가 자기정보를 바꾸려고 하는지 검증
        userService.isSameUser(id, sessionUserDto);

        // 수정 진행
        UserResponseDto userResponseDto = userService.modifyProfile(id, updateProfileDto);

        // 세션 value 갱신
        sessionUserDto.setEmail(userResponseDto.getEmail());
        sessionUserDto.setNickname(userResponseDto.getNickname());

        // 세션 갱신
        session.setAttribute(Const.USER,sessionUserDto);


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

        // 검증을 위한 다운 캐스팅
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(Const.USER);

        // 유저가 자기정보를 바꾸려고 하는지 검증
        userService.isSameUser(id, sessionUserDto);

        // 비밀번호 변경 로직
        userService.updatePassword(id, updatePasswordDto.getOldPassword(), updatePasswordDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }



    // 유저 삭제 ( 회원 탈퇴 )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id,
            @RequestHeader("password") String password,
            HttpServletRequest request
    ){

        // 세션 가져오기
        HttpSession session = request.getSession();

        // 검증을 위한 다운 캐스팅
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(Const.USER);

        // 유저가 자기정보를 바꾸려고 하는지 검증
        userService.isSameUser(id, sessionUserDto);

        // 회원탈퇴 수행
        userService.deleteUser(id, password);

        // 세션 삭제
        session.invalidate();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
