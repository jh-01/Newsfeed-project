package org.example.newsfeedproject.comment.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.comment.dto.QCommentResponse;
import static org.example.newsfeedproject.comment.entity.QComment.comment;
import static org.example.newsfeedproject.comment.entity.QCommentImage.commentImage;
import static org.example.newsfeedproject.like.entity.QCommentLike.commentLike;

import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.comment.exception.CommentNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QCommentRepositoryImpl implements QCommentRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public CommentResponse findById(Long id, Long userId){
        return queryFactory.select(
                        new QCommentResponse(
                                comment.id,
                                comment.feed.id,
                                comment.user.nickname,
                                comment.comments,

                                // 이미지
                                JPAExpressions.select(commentImage.path)
                                        .from(commentImage)
                                        .where(commentImage.comment.id.eq(comment.id))
                                        .limit(1),

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
                        ))
                .from(comment)
                .where(comment.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<CommentResponse> findAllByFeedId(Long feedId, Long userId) {
        return queryFactory.select(
                new QCommentResponse(
                        comment.id,
                        comment.feed.id,
                        comment.user.nickname,
                        comment.comments,

                        // 이미지
                        JPAExpressions.select(commentImage.path)
                                .from(commentImage)
                                .where(commentImage.comment.id.eq(comment.id)),

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

    @Override
    public void deleteByFeedId(Long feedId) {
        queryFactory.delete(comment).where(comment.feed.id.eq(feedId)).execute();
    }

    @Override
    public Comment findByIdOrElseThrow(Long id) {
        Comment result = queryFactory.selectFrom(comment)
                .where(comment.id.eq(id))
                .fetchOne();

        if (result == null) {
            throw new CommentNotFoundException(id);
        }
        return result;
    }
}
