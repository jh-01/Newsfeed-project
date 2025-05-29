package org.example.newsfeedproject.user.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newsfeedproject.user.dto.SessionUserDto;
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

    // 유저 생성 - 회원가입
    public UserResponseDto signup(String email, String password, String nickName) {

        // BCrypt 로 인코딩
        String encodedPassword = BCrypt.withDefaults().hashToString(10, password.toCharArray());

        // 인코딩된 정보로 유저생성
        User user = new User(email, encodedPassword, nickName);

        User saveUser = userRepository.save(user);

        // 닉네임 같은 경우 오류 로직 구상해야함

        return new UserResponseDto(saveUser.getId(), saveUser.getEmail(), saveUser.getNickname(), saveUser.getCreatedAt(), saveUser.getModifiedAt());

    }

    // 유저 조회
    public UserResponseDto findById(Long id) {
        User user = userRepository.findByIdOrElseThrow(id);

        return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getModifiedAt());
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
    public UserResponseDto modifyProfile(Long id,String email, String nickname) {

        // Optional 에서 이미 예외처리를 했기 때문에 null 이 아님.
        User user = userRepository.findByIdOrElseThrow(id);

        user.modifyProfile(email, nickname);

        userRepository.save(user);

        return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(),user.getModifiedAt());
    }

    // 비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findByIdOrElseThrow(id);

        // 비밀번호 검증
        BCrypt.Result result =  BCrypt.verifyer().verify(oldPassword.toCharArray(),user.getPassword());

        // 비밀번호가 다르면 400 반환
        if(!result.verified) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 비밀번호 변경
        user.updatePassword(newPassword);
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(Long id, String password) {
        User user = userRepository.findByIdOrElseThrow(id);

        // 헤더에서 받아온 비밀번호 검증
        BCrypt.verifyer().verify(password.toCharArray(),user.getPassword());

        // 삭제여부 true 로 변경
        user.setIs_deleted(true);

        // 활성화한 삭제여부 반영
        userRepository.save(user);
    }

    // 로그인한 유저가 자기정보를 바꾸려고 하는지 검증
    public void isSameUser(Long id, SessionUserDto sessionUserDto) {

        // 바꾸려고 하는 유저 정보
        User user = userRepository.findByIdOrElseThrow(id);

        // 세션에 저장된 사용자의 이메일
        String userEmail = sessionUserDto.getEmail();

        // 비교된 이메일이 다르면 401 반환
        if(!userEmail.equals(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }
}
