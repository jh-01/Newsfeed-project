package org.example.newsfeedproject.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
public class CommentModifyRequest {
    @NotNull
    private String comments;
}
