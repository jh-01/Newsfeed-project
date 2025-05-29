package org.example.newsfeedproject.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newsfeedproject.user.dto.UpdateProfileDto;
import org.example.newsfeedproject.user.dto.UserResponseDto;
import org.example.newsfeedproject.user.entity.User;
import org.example.newsfeedproject.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto signup(String email, String password, String nickName) {

        User user = new User(email, password, nickName);
        User saveUser = userRepository.save(user);

        // 닉네임 같은 경우 오류 로직 구상해야함

        return new UserResponseDto(saveUser.getId(),
                saveUser.getEmail(),
                saveUser.getNickname(),
                saveUser.getCreatedAt(),
                saveUser.getModifiedAt());

    }

    // 유저 조회
    public UserResponseDto findById(Long id) {
        User user = userRepository.findByIdOrElseThrow(id);

        return new UserResponseDto(user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }



    // 전체 유저 조회
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    @Transactional
    // 유저 프로필 수정
    public UserResponseDto modifyProfile(Long id, UpdateProfileDto request) {

        User user = userRepository.findByIdOrElseThrow(id);

        /*
           값이 null이 아닐 때만 수정
           email 이나 nickname 값 중 하나만 들어와도 변경
         */
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
        }
        if (request.getNickname() != null && !request.getNickname().isBlank()) {
            user.setNickname(request.getNickname());
        }

        // 예외처리 작성 ( NPE ) 해결해야함

        userRepository.save(user);

        return new UserResponseDto(user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

    // 비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword,String newPassword) {
        User user = userRepository.findByIdOrElseThrow(id);

        // 비밀번호 암호화 필요
        if(!user.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다");
        }

        user.updatePassword(newPassword);
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findByIdOrElseThrow(id);

        // 비밀번호 받아와서 삭제할지 결정해야함
        // 받아와서 삭제할거면 비밀번호 디코딩 후 삭제처리

        userRepository.delete(user);
    }
}
