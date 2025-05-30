package org.example.newsfeedproject.user.login_exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoginFailureHandler {

    // 비밀번호 틀리면 401 반환
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<String> PasswordMismatchException (PasswordMismatchException e) {
        return new ResponseEntity<>("로그인 실패 : 비밀번호가 틀립니다.",HttpStatus.UNAUTHORIZED);
    }

    // 삭제된 유저면 400 반환
    @ExceptionHandler(DeletedUserException.class)
    public ResponseEntity<String> DeletedUserException (DeletedUserException e) {
        return new ResponseEntity<>("로그인 실패 : 탈퇴한 유저입니다.", HttpStatus.BAD_REQUEST);
    }

}
