package org.example.newsfeedproject.comment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeedNotFoundException extends RuntimeException {
    public FeedNotFoundException(Long id){
        super("게시글을 찾을 수 없습니다. id = " + id);
    }
}
