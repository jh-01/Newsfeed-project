package org.example.newsfeedproject.like.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.comment.repository.CommentRepository;
import org.example.newsfeedproject.like.entity.CommentLike;
import org.example.newsfeedproject.like.repository.CommentLikeRepository;
import org.example.newsfeedproject.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    public void commentLike(Long id, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        Comment comment = commentRepository.findByIdOrElseThrow(id);

        CommentLike commentLike = commentLikeRepository.findByUserIdAndCommentId(user.getId(), comment.getId());

        if (commentLike != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복 좋아요는 불가합니다.");
        }

        likeRepository.save(commentLike);

    }

    public void deleteCommentLike(Long id, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        Comment comment = commentRepository.findByIdOrElseThrow(id);

        CommentLike commentLike = new CommentLike(user, comment);

        likeRepository.delete(commentLike);

    }
}
