package org.example.newsfeedproject.comment.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.comment.repository.CommentRepository;
import org.example.newsfeedproject.feed.entity.Feed;
import org.example.newsfeedproject.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private static CommentRepository commentRepository;
//    private static FeedRepository feedRepository;
//    private static UserRepository userRepository;

//    public CommentService(CommentRepository commentRepository, UserRepository userRepository, FeedRepository feedRepository) {
//        this.commentRepository = commentRepository;
//        this.userRepository = userRepository;
//        this.feedRepository = feedRepository;
//    }


    @Transactional
    public CommentResponse saveComment(Long feedId, Long userId, String comments){
//        final Feed feed = feedRepository.findById(feedId)
//                .orElseThrow(() -> new EntityNotFoundException("Schedule not found. id = " + feedId));
//        final User feed = userRepository.findById(feedId)
//                .orElseThrow(() -> new EntityNotFoundException("Schedule not found. id = " + feedId));

        Feed feed = new Feed(feedId);
        User user = new User(userId);
        final Comment comment = Comment.builder()
                .feed(feed)
                .user(user)
                .comments(comments)
                .build();
        Comment saved = commentRepository.save(comment);
        return Comment.toDto(saved);
    }
}
