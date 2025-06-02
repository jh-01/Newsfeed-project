package org.example.newsfeedproject.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CommentModifyRequest {
    @NotNull
    private String comments;
    private MultipartFile image;
}
