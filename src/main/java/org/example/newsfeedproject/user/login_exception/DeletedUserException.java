package org.example.newsfeedproject.user.login_exception;

public class DeletedUserException extends  RuntimeException{

    public DeletedUserException(String message) {
        super(message);
    }

}
