package org.example.newsfeedproject.comment.exception;

public class CommentUnauthorizedException extends RuntimeException {
    public CommentUnauthorizedException() {
        super("자신의 댓글만 수정 또는 삭제할 수 있습니다.");
    }
}
