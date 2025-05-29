package org.example.newsfeedproject.like.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.like.service.FeedLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes/feeds")
@RequiredArgsConstructor
public class FeedLikeController {

    private final FeedLikeService feedlikeService;

    @PostMapping
    public ResponseEntity<Void> feedLike(@RequestBody Long id, HttpServletRequest request) {

        feedlikeService.feedLike(id, request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedLike(@PathVariable Long id, HttpServletRequest request){

        feedlikeService.deleteFeedLike(id, request);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
