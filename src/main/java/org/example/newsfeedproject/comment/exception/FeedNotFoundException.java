package org.example.newsfeedproject.comment.exception;

public class FeedNotFoundException extends RuntimeException {
    public FeedNotFoundException(Long id){
        super("게시글을 찾을 수 없습니다. id = " + id);
    }
}
