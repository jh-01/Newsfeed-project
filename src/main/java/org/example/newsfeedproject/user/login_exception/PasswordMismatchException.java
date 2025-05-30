package org.example.newsfeedproject.user.login_exception;

public class PasswordMismatchException extends RuntimeException{

    public PasswordMismatchException(String message) {
        super(message);
    }

}
