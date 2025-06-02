package org.example.newsfeedproject.like.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.comment.repository.CommentRepository;
import org.example.newsfeedproject.like.entity.CommentLike;
import org.example.newsfeedproject.like.repository.CommentLikeRepository;
import org.example.newsfeedproject.user.dto.SessionUserDto;
import org.example.newsfeedproject.user.entity.User;
import org.example.newsfeedproject.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public void commentLike(Long id, HttpServletRequest request) {

        SessionUserDto user = (SessionUserDto) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(user.getId());

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다."));

        if (me.getId().equals(comment.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인의 댓글에 좋아요는 불가합니다.");
        }

        CommentLike commentLike = commentLikeRepository.findByUserIdAndCommentId(me, comment);

        if (commentLike != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복 좋아요는 불가합니다.");
        }

        commentLike = new CommentLike(me, comment);

        commentLikeRepository.save(commentLike);

    }

    public void deleteCommentLike(Long id, HttpServletRequest request) {

        SessionUserDto user = (SessionUserDto) request.getSession().getAttribute("user");
        User me = userRepository.findByIdOrElseThrow(user.getId());

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다."));

        CommentLike commentLike = commentLikeRepository.findByUserIdAndCommentId(me, comment);

        if (commentLike == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "취소할 좋아요가 없습니다.");
        }

        commentLikeRepository.delete(commentLike);

    }
}
