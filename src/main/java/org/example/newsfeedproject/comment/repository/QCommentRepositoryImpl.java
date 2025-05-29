package org.example.newsfeedproject.comment.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.comment.dto.QCommentResponse;
import static org.example.newsfeedproject.comment.entity.QComment.comment;
import static org.example.newsfeedproject.like.entity.QCommentLike.commentLike;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QCommentRepositoryImpl implements QCommentRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentResponse> findAllByFeedId(Long feedId, Long userId) {
        return queryFactory.select(
                new QCommentResponse(
                        comment.id,
                        comment.feed.id,
                        comment.user.nickname,
                        comment.comments,
                        comment.createdAt,
                        comment.modifiedAt,

                        // likeCount
                        JPAExpressions.select(commentLike.count())
                                .from(commentLike)
                                .where(commentLike.commentId.id.eq(comment.id)),

                        // isLiked
                        JPAExpressions.selectOne()
                                .from(commentLike)
                                .where(
                                        commentLike.commentId.id.eq(comment.id)
                                                .and(commentLike.userId.id.eq(userId))
                                )
                                .exists()

                )).from(comment)
                .where(comment.feed.id.eq(feedId))
                .fetch();
    }
}
