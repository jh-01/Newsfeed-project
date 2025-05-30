package org.example.newsfeedproject.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentModifyRequest {
    @NotNull
    private String comments;
}
