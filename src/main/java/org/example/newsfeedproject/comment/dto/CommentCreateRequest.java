package org.example.newsfeedproject.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentCreateRequest {
    @NotNull
    private Long feedId;
    @NotNull
    private String comments;
}
