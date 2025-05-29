package org.example.newsfeedproject.comment.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.comment.dto.CommentResponse;
import org.example.newsfeedproject.comment.dto.QCommentResponse;
import org.example.newsfeedproject.comment.entity.Comment;
import static org.example.newsfeedproject.comment.entity.QComment.comment;
import static org.example.newsfeedproject.user.entity.QUser.user;
import com.querydsl.jpa.JPAExpressions;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QCommentRepositoryImpl implements QCommentRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentResponse> findAllByFeedId(Long feedId, Long userId) {
//        return queryFactory.select(
//                new QCommentResponse(
//                        comment.id,
//                        comment.feed.id,
//                        comment.user.nickname,
//                        comment.comments,
//                        comment.createdAt,
//                        comment.modifiedAt,
//
//                        // likeCount
//                        JPAExpressions.select(commentLike.count())
//                                .from(commentLike)
//                                .where(commentLike.comment.id.eq(comment.id)),
//
//                        // isLiked
//                        JPAExpressions.selectOne()
//                                .from(commentLike)
//                                .where(
//                                        commentLike.comment.id.eq(comment.id)
//                                                .and(commentLike.user.id.eq(userId))
//                                )
//                                .exists()
//
//                )).from(comment)
//                .where(comment.feed.id.eq(feedId))
//                .fetch();

        // CommentLike 생기면 위 코드로 대체
        return queryFactory.select(
                        new QCommentResponse(
                                comment.id,
                                comment.feed.id,
                                comment.user.nickname,
                                comment.comments,
                                comment.createdAt,
                                comment.modifiedAt,
                                // likeCount (예: 0L 임시값)
                                Expressions.constant(0L),
                                // isLiked (예: true 임시값)
                                Expressions.constant(true)
                        )).from(comment)
                .where(comment.feed.id.eq(feedId))
                .fetch();
    }
}
