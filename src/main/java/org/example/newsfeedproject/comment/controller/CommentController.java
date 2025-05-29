package org.example.newsfeedproject.comment.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.comment.dto.CommentCreateRequest;
import org.example.newsfeedproject.comment.dto.CommentModifyRequest;
import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.comment.service.CommentService;
import org.example.newsfeedproject.user.dto.SessionUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.newsfeedproject.user.constant.Const;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> create(
            HttpServletRequest request,
            @RequestBody CommentCreateRequest createRequest
            ){
        HttpSession session = request.getSession(false);
        if(session == null) {
            throw new RuntimeException("로그인을 해주세요.");
        }

        // session에 저장된 유저정보 조회
        SessionUserDto loginUser = (SessionUserDto) session.getAttribute(Const.USER);

        // 지금은 아이디에 임의의 값 전달, 추후 세션에 아이디 저장하면 해당 값 불러오기
        CommentResponse response = commentService.saveComment(createRequest.getFeedId(), loginUser.getId(), createRequest.getComments());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/feeds/{id}")
    public ResponseEntity<List<CommentResponse>> getAllComments(
            HttpServletRequest request,
            @PathVariable Long id
    ){
        HttpSession session = request.getSession(false);
        if(session == null) {
            throw new RuntimeException("로그인을 해주세요.");
        }
        // session에 저장된 유저정보 조회
        SessionUserDto loginUser = (SessionUserDto) session.getAttribute(Const.USER);

        // 지금은 아이디에 임의의 값 전달, 추후 세션에 아이디 저장하면 해당 값 불러오기
        List<CommentResponse> commentResponseList = commentService.getAllComments(id, loginUser.getId());
        return ResponseEntity.ok(commentResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentResponse> getComment(
            @PathVariable Long id
    ){
        // 지금은 아이디에 임의의 값 전달, 추후 세션에 아이디 저장하면 해당 값 불러오기
        CommentResponse commentResponse = commentService.getComment(id);
        return ResponseEntity.ok(commentResponse);
    }

    @PutMapping("/id")
    public ResponseEntity<CommentResponse> modifyComment(
            HttpServletRequest request,
            @PathVariable Long id,
            CommentModifyRequest modifyRequest
    ){
        HttpSession session = request.getSession(false);
        if(session == null) {
            throw new RuntimeException("로그인을 해주세요.");
        }
        // session에 저장된 유저정보 조회
        SessionUserDto loginUser = (SessionUserDto) session.getAttribute(Const.USER);

        // 지금은 아이디에 임의의 값 전달, 추후 세션에 아이디 저장하면 해당 값 불러오기
        CommentResponse response = commentService.modifyComment(id, loginUser.getId(), modifyRequest.getComments());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteComment(
            HttpServletRequest request,
            @PathVariable Long id
    ) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            throw new RuntimeException("로그인을 해주세요.");
        }

        // 지금은 아이디에 임의의 값 전달, 추후 세션에 아이디 저장하면 해당 값 불러오기
        commentService.deleteComment(id);
        return ResponseEntity.ok("댓글 삭제가 완료되었습니다.");
    }
}
