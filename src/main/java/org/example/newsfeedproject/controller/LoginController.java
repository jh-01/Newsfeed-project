package org.example.newsfeedproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.constant.Const;
import org.example.newsfeedproject.dto.LoginReqeustDto;
import org.example.newsfeedproject.dto.LoginResponseDto;
import org.example.newsfeedproject.dto.SessionUserDto;
import org.example.newsfeedproject.loginservice.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginReqeustDto dto,
            HttpServletRequest request
            ) {

        // 유저 정보를 받아옴
        User user = loginService.bringUserInfo(dto.getEmail(), dto.getPassword());

        // 비밀번호가 틀리면 401 반환
        if(!user.getPassword().equals(dto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // 삭제된 유저이면 400 반환
        if(user.getIsDeleted) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 세션생성
        HttpSession session = request.getSession();

        // 세션에 저장할 값에 닉네임과 이메일만 남김.
        SessionUserDto sessionDto = new SessionUserDto(
                user.getNickname(),
                user.getEmail()
        );

        // 세션에 값 저장
        session.setAttribute(Const.USER,sessionDto);

        // 응답에 사용할 dto 생성
        LoginResponseDto responseDto = new LoginResponseDto(
                user.getNickname(),
                user.getEmail(),
                "로그인 성공!"
        );

        // 로그인 성공문 반환
        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }

}
