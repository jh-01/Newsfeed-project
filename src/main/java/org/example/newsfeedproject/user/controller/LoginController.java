package org.example.newsfeedproject.user.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.constant.Const;
import org.example.newsfeedproject.user.dto.LoginReqeustDto;
import org.example.newsfeedproject.user.dto.LoginResponseDto;
import org.example.newsfeedproject.user.dto.SessionUserDto;
import org.example.newsfeedproject.user.entity.User;
import org.example.newsfeedproject.user.login_exception.DeletedUserException;
import org.example.newsfeedproject.user.login_exception.PasswordMismatchException;
import org.example.newsfeedproject.user.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final LoginService loginService;



    // 로그인 기능
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginReqeustDto dto,
            HttpServletRequest request
            ) {

        // 유저 정보를 모두 받아옴
        User user = loginService.bringUserInfo(
                dto.getEmail(),
                dto.getPassword());

        // 비밀번호가 틀리면 401 반환 -> BCrypt 로 검증
        if(!BCrypt.verifyer().verify(
                dto.getPassword().toCharArray(),
                user.getPassword()
        ).verified) {
            throw new PasswordMismatchException("비밀번호 오류");
        }

        // 삭제된 유저이면 400 반환
        if(user.is_deleted()) {
            throw new DeletedUserException("탈퇴한 유저");
        }

        // 세션생성
        HttpSession session = request.getSession();

        // 세션에 저장할 값에 닉네임, 이메일, id 값만 남김.
        SessionUserDto sessionDto = new SessionUserDto(
                user.getNickname(),
                user.getEmail(),
                user.getId()
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



    // 로그아웃 기능
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        // 요청에 세션이 없으면 null 반환, 있으면 있는걸 반환
        HttpSession session = request.getSession(false);

        // 세션 존재시 삭제함
        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>("로그아웃 성공!",HttpStatus.OK);

    }


}
