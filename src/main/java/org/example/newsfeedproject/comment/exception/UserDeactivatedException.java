package org.example.newsfeedproject.comment.exception;

public class UserDeactivatedException extends RuntimeException{
    public UserDeactivatedException(Long id){
        super("탈퇴한 유저입니다. id = " + id);
    }
}
