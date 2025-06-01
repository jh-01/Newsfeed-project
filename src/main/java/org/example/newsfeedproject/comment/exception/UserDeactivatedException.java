package org.example.newsfeedproject.comment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserDeactivatedException extends RuntimeException{
    public UserDeactivatedException(Long id){
        super("탈퇴한 유저입니다. id = " + id);
    }
}
