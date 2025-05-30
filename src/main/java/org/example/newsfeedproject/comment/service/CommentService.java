package org.example.newsfeedproject.comment.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.comment.exception.CommentNotFoundException;
import org.example.newsfeedproject.comment.exception.CommentUnauthorizedException;
import org.example.newsfeedproject.comment.repository.CommentRepository;
import org.example.newsfeedproject.feed.entity.Feed;
import org.example.newsfeedproject.like.repository.CommentLikeRepository;
import org.example.newsfeedproject.user.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

      private static CommentRepository commentRepository;
      private static CommentLikeRepository commentLikeRepository;
//    private static FeedRepository feedRepository;
//    private static UserRepository userRepository;
//
//    public CommentService(CommentRepository commentRepository, UserRepository userRepository, FeedRepository feedRepository) {
//        this.commentRepository = commentRepository;
//        this.userRepository = userRepository;
//        this.feedRepository = feedRepository;
//    }


    @Transactional
    public CommentResponse saveComment(Long feedId, Long userId, String comments){
//        final Feed feed = feedRepository.findById(feedId)
//                .orElseThrow(() -> new FeedNotFoundException("Feed not found. id = " + feedId));
//        final User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found. id = " + userId));
        // 탈퇴한 유저일 경우 오류 반환
//

        Feed feed = new Feed(feedId);
        User user = new User(userId);
        final Comment comment = Comment.builder()
                .feed(feed)
                .user(user)
                .comments(comments)
                .build();
        Comment saved = commentRepository.save(comment);

        // 처음 작성 시 초기값 등록
        return CommentResponse.of(saved, false, 0L);
    }

    @Transactional
    public List<CommentResponse> getAllComments(Long feedId, Long userId){
        // 피드 존재 여부 확인
//        if (!feedRepository.existsById(feedId)) {
//            throw new FeedNotFoundException(feedId);
//        }
        return commentRepository.findAllByFeedId(feedId, userId);
    }

    @Transactional
    public CommentResponse getComment(Long id){
        final Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isEmpty()) throw new CommentNotFoundException(id);
        return Comment.toDto(optionalComment.get());
    }

    @Transactional
    public CommentResponse modifyComment(Long id, Long userId, String comments){
        Comment comment = commentRepository.findById(id)
                .orElseThrow();

        if(!Objects.equals(comment.getUser().getId(), userId))
            throw new CommentUnauthorizedException();
        comment.setComments(comments);

        commentRepository.save(comment);

        return Comment.toDto(comment);
    }

    @Transactional
    public void deleteComment(Long id,Long userId){
        Comment comment = commentRepository.findById(id)
                .orElseThrow();

        if(!Objects.equals(comment.getUser().getId(), userId))
            throw new CommentUnauthorizedException();

        commentRepository.deleteById(id);
    }

    // 피드 삭제 시 동시 삭제
    @Transactional
    public void deleteAllComments(Long feedId){
        commentRepository.deleteByFeedId(feedId);
    }
}
