package org.example.newsfeedproject.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.newsfeedproject.comment.entity.Comment;
import org.example.newsfeedproject.comment.entity.CommentImage;
import org.example.newsfeedproject.comment.repository.CommentImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CommentImageService {
    private final CommentImageRepository commentImageRepository;

    public String uploadImage(MultipartFile commentImage){
        try {
            String uploadsDir  = "src/main/resources/static/uploads/images/";

            // 파일 이름 생성
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + commentImage.getOriginalFilename();
            // 실제 파일이 저장될 경로
            String filePath = uploadsDir  + fileName;
            // DB에 저장할 경로 문자열
            String dbFilePath = "/uploads/thumbnails/" + fileName;

            Path path = Paths.get(filePath); // Path 객체 생성
            Files.createDirectories(path.getParent()); // 디렉토리 생성
            Files.write(path, commentImage.getBytes()); // 디렉토리에 파일 저장\
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
