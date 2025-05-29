package org.example.newsfeedproject.like.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.like.service.CommentLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes/comments")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping
    public ResponseEntity<Void> commentLike(@RequestBody Long id, HttpServletRequest request) {

        commentLikeService.commentLike(id, request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentLike(@PathVariable Long id, HttpServletRequest request){

        commentLikeService.deleteCommentLike(id, request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
