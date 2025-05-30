package org.example.newsfeedproject.comment.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("존재하지 않는 댓글입니다. id = " + id);
    }
}
