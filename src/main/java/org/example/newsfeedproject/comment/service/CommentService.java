package org.example.newsfeedproject.comment.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.comment.repository.CommentRepository;
import org.example.newsfeedproject.feed.entity.Feed;
import org.example.newsfeedproject.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public List<CommentResponse> getAllComments(Long feedId, Long userId){
        final List<CommentResponse> commentList = commentRepository.findAllByFeedId(feedId, userId);
        return commentList;
    }

    @Transactional
    public CommentResponse getComment(Long id){
        final Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isEmpty()) throw new RuntimeException("존재하지 않은 댓글입니다.");
        return Comment.toDto(optionalComment.get());
    }

    @Transactional
    public CommentResponse modifyComment(Long id, Long userId, String comments){
        Comment comment = commentRepository.findById(id)
                .orElseThrow();

        if(comment.getUser().getId() != userId)
            throw new RuntimeException("자신의 댓글만 수정할 수 있습니다.");
        comment.setComments(comments);

        commentRepository.save(comment);

        return Comment.toDto(comment);
    }

    @Transactional
    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }
}
