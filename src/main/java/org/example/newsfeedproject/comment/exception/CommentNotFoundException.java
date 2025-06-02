package org.example.newsfeedproject.comment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("존재하지 않는 댓글입니다. id = " + id);
    }
}
