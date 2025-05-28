package org.example.newsfeedproject.loginservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    // 속성으로 갖는 레포지토리
    private final UserRepository userRepository;

    // 유저정보 가져오는 메서드
    public User bringUserInfo(String email, String password) {

        // 레포지토리에 email 로 유저정보를 조회하는 메서드를 만들어야함. 아래코드를 추가해야함.
        // Optional<User> findByEmail(String email);
        Optional<User> byEmail = userRepository.findByEmail(email);

        // Optional 에서 유저정보 꺼내기
        // 존재하지 않는 이메일 입력시 404 반환
        byEmail.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"이메일이 존재하지 않습니다."));

    }

}
