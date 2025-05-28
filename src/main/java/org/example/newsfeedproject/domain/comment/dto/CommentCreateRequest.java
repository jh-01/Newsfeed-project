package org.example.newsfeedproject.domain.comment.dto;

import jakarta.validation.constraints.NotNull;

public class CommentCreateRequest {
    @NotNull
    private Long feedId;
    @NotNull
    private String comments;
}
